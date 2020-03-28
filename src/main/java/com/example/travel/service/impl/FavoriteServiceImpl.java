package com.example.travel.service.impl;

import com.example.travel.dao.FavoriteDao;
import com.example.travel.dao.ScenicSpotDao;
import com.example.travel.dao.TicketDao;
import com.example.travel.dao.UserDao;
import com.example.travel.pojo.Favorite;
import com.example.travel.pojo.ScenicSpot;
import com.example.travel.pojo.Ticket;
import com.example.travel.pojo.User;
import com.example.travel.request.*;
import com.example.travel.response.*;
import com.example.travel.service.FavoriteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteDao favoriteDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ScenicSpotDao scenicSpotDao;
    @Autowired
    private TicketDao ticketDao;

    @Override
    public Integer insertFavorite(AddFavoriteRequest addFavoriteRequest) {
        Favorite oldFavorite = favoriteDao.selectFavoriteByUserIdAndScenicId(addFavoriteRequest.getUserId(), addFavoriteRequest.getScenicSpotId());
        if(oldFavorite==null) {
            Favorite favorite = new Favorite();
            BeanUtils.copyProperties(addFavoriteRequest, favorite);
            favorite.setCreateTime(new Date());
            return favoriteDao.insertFavorite(favorite);
        }else return 0;
    }

    @Override
    public Integer deleteFavorite(IdsRequest idsRequest) {
        return favoriteDao.deleteFavorite(idsRequest.getIds());
    }

    @Override
    public FavoriteResponse selectFavoriteById(IdRequest idRequest) {
        Favorite favorite = favoriteDao.selectFavoriteById(idRequest.getId());
        FavoriteResponse favoriteResponse = changeFavoriteResponse(favorite);
        return favoriteResponse;
    }

    @Override
    public AllFavoriteResponse selectAllFavorite(PageNumRequest pageNumRequest) {
        PageHelper.startPage(pageNumRequest.getPageNum(),10);
        List<Favorite> favorites = favoriteDao.selectAllFavorite();
        PageInfo<Favorite> pageInfo = new PageInfo<>(favorites);

        List<Favorite> favoriteList = pageInfo.getList();
        AllFavoriteResponse allFavoriteResponse = new AllFavoriteResponse();
        List<FavoriteResponse> list = new ArrayList<>();
        for(Favorite favorite : favoriteList){
            FavoriteResponse favoriteResponse = changeFavoriteResponse(favorite);
            list.add(favoriteResponse);
        }
        allFavoriteResponse.setFavoriteResponseList(list);
        allFavoriteResponse.setTotal(favoriteDao.countAllFavorite());
        return allFavoriteResponse;
    }

    @Override
    public AllFavoriteUserResponse selectFavoriteByUserId(SelectFavoriteByUserIdRequest selectFavoriteByUserIdRequest) {
        if(!StringUtils.isEmpty(selectFavoriteByUserIdRequest.getId())) {
            PageHelper.startPage(selectFavoriteByUserIdRequest.getPageNum(), 10);
            List<Favorite> favorites = favoriteDao.selectFavoriteByUserId(selectFavoriteByUserIdRequest.getId());
            PageInfo<Favorite> pageInfo = new PageInfo<>(favorites);

            List<Favorite> favoriteList = pageInfo.getList();
            if(!CollectionUtils.isEmpty(favoriteList)) {
                AllFavoriteUserResponse allFavoriteUserResponse = new AllFavoriteUserResponse();
                List<FavoriteUserResponse> list = new ArrayList<>();
                for (Favorite favorite : favoriteList) {
                    FavoriteUserResponse favoriteUserResponse = new FavoriteUserResponse();

                    ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(favorite.getScenicSpotId());
                    BeanUtils.copyProperties(scenicSpot, favoriteUserResponse);

                    Ticket ticket = ticketDao.selectTicketByScenicId(scenicSpot.getId());
                    favoriteUserResponse.setAdultTicketPrice(ticket.getAdultTicketPrice());

                    favoriteUserResponse.setId(favorite.getId());
                    favoriteUserResponse.setScenicSpotId(favorite.getScenicSpotId());
                    favoriteUserResponse.setCreateTime(changeDate(favorite.getCreateTime()));
                    favoriteUserResponse.setFavoritePeopleNumber(favoriteDao.countFavorteByScenicId(favorite.getScenicSpotId()));
                    list.add(favoriteUserResponse);
                }
                allFavoriteUserResponse.setFavoriteUserResponseList(list);
                allFavoriteUserResponse.setTotal(favoriteDao.countFavoriteByUserId(selectFavoriteByUserIdRequest.getId()));
                return allFavoriteUserResponse;
            }else return null;
        }else return null;
    }

    @Override
    public AllFavoriteScenicResponse selectFavoriteByScenicId(SelectFavoriteByScenicIdRequest selectFavoriteByScenicIdRequest) {
        PageHelper.startPage(selectFavoriteByScenicIdRequest.getPageNum(),10);
        List<Favorite> favorites = favoriteDao.selectFavoriteByScenicId(selectFavoriteByScenicIdRequest.getScenicSpotId());
        PageInfo<Favorite> pageInfo = new PageInfo<>(favorites);

        List<Favorite> favoriteList = pageInfo.getList();
        AllFavoriteScenicResponse allFavoriteScenicResponse = new AllFavoriteScenicResponse();
        List<FavoriteScenicResponse> list = new ArrayList<>();
        for(Favorite favorite : favoriteList){
            FavoriteScenicResponse favoriteScenicResponse = new FavoriteScenicResponse();
            favoriteScenicResponse.setCreateTime(changeDate(favorite.getCreateTime()));

            User user = userDao.selectUserById(favorite.getUserId());
            favoriteScenicResponse.setNickName(user.getNickName());

            list.add(favoriteScenicResponse);
        }
        allFavoriteScenicResponse.setFavoriteScenicResponseList(list);
        allFavoriteScenicResponse.setTotal(favoriteDao.countFavorteByScenicId(selectFavoriteByScenicIdRequest.getScenicSpotId()));
        return allFavoriteScenicResponse;
    }

    private FavoriteResponse changeFavoriteResponse(Favorite favorite){
        FavoriteResponse favoriteResponse = new FavoriteResponse();
        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(favorite.getScenicSpotId());
        favoriteResponse.setId(favorite.getId());
        favoriteResponse.setScenicSpotName(scenicSpot.getScenicSpotName());
        favoriteResponse.setScenicSpotAddress(scenicSpot.getScenicSpotAddress());
        favoriteResponse.setScenicSpotSynopsis(scenicSpot.getScenicSpotSynopsis());
        favoriteResponse.setImg(scenicSpot.getImg());

        User user = userDao.selectUserById(favorite.getUserId());
        favoriteResponse.setNickName(user.getNickName());
        favoriteResponse.setCreateTime(changeDate(favorite.getCreateTime()));
        return favoriteResponse;
    }

    private String changeDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
