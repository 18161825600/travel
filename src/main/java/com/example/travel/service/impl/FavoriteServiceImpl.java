package com.example.travel.service.impl;

import com.example.travel.dao.FavoriteDao;
import com.example.travel.dao.ScenicSpotDao;
import com.example.travel.dao.UserDao;
import com.example.travel.pojo.Favorite;
import com.example.travel.pojo.ScenicSpot;
import com.example.travel.pojo.User;
import com.example.travel.request.AddFavoriteRequest;
import com.example.travel.response.FavoriteResponse;
import com.example.travel.response.FavoriteScenicResponse;
import com.example.travel.response.FavoriteUserResponse;
import com.example.travel.service.FavoriteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteDao favoriteDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ScenicSpotDao scenicSpotDao;

    @Override
    public Integer insertFavorite(AddFavoriteRequest addFavoriteRequest) {
        Favorite favorite = new Favorite();
        BeanUtils.copyProperties(addFavoriteRequest,favorite);
        favorite.setCreateTime(new Date());
        return favoriteDao.insertFavorite(favorite);
    }

    @Override
    public Integer deleteFavorite(List<Long> ids) {
        return favoriteDao.deleteFavorite(ids);
    }

    @Override
    public FavoriteResponse selectFavoriteById(Long id) {
        Favorite favorite = favoriteDao.selectFavoriteById(id);
        FavoriteResponse favoriteResponse = changeFavoriteResponse(favorite);
        favoriteResponse.setTotal(1);
        return favoriteResponse;
    }

    @Override
    public PageInfo<FavoriteResponse> selectAllFavorite(Integer pageNum) {
        List<Favorite> favorites = favoriteDao.selectAllFavorite();
        PageHelper.startPage(pageNum,10);
        List<FavoriteResponse> list = new ArrayList<>();
        for(Favorite favorite : favorites){
            FavoriteResponse favoriteResponse = changeFavoriteResponse(favorite);
            favoriteResponse.setTotal(favoriteDao.countAllFavorite());
            list.add(favoriteResponse);
        }
        PageInfo<FavoriteResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<FavoriteUserResponse> selectFavoriteByUserId(Long userId, Integer pageNum) {
        List<Favorite> favorites = favoriteDao.selectFavoriteByUserId(userId);
        PageHelper.startPage(pageNum,10);
        List<FavoriteUserResponse> list = new ArrayList<>();
        for(Favorite favorite : favorites){
            FavoriteUserResponse favoriteUserResponse = new FavoriteUserResponse();

            ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(favorite.getScenicSpotId());
            BeanUtils.copyProperties(scenicSpot,favoriteUserResponse);

            favoriteUserResponse.setCreateTime(favorite.getCreateTime());
            favoriteUserResponse.setTotal(favoriteDao.countFavoriteByUserId(userId));

            list.add(favoriteUserResponse);
        }
        PageInfo<FavoriteUserResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<FavoriteScenicResponse> selectFavoriteByScenicId(Long scenicSpotId, Integer pageNum) {
        List<Favorite> favorites = favoriteDao.selectFavoriteByScenicId(scenicSpotId);
        PageHelper.startPage(pageNum,10);
        List<FavoriteScenicResponse> list = new ArrayList<>();
        for(Favorite favorite : favorites){
            FavoriteScenicResponse favoriteScenicResponse = new FavoriteScenicResponse();
            favoriteScenicResponse.setCreateTime(favorite.getCreateTime());
            favoriteScenicResponse.setTotal(favoriteDao.countFavorteByScenicId(scenicSpotId));

            User user = userDao.selectUserById(favorite.getUserId());
            favoriteScenicResponse.setNickName(user.getNickName());

            list.add(favoriteScenicResponse);
        }
        PageInfo<FavoriteScenicResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public FavoriteResponse changeFavoriteResponse(Favorite favorite){
        FavoriteResponse favoriteResponse = new FavoriteResponse();
        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(favorite.getScenicSpotId());
        favoriteResponse.setScenicSpotName(scenicSpot.getScenicSpotName());
        favoriteResponse.setScenicSpotAddress(scenicSpot.getScenicSpotAddress());
        favoriteResponse.setScenicSpotSynopsis(scenicSpot.getScenicSpotSynopsis());
        favoriteResponse.setImg(scenicSpot.getImg());

        User user = userDao.selectUserById(favorite.getUserId());
        favoriteResponse.setNickName(user.getNickName());
        favoriteResponse.setCreateTime(favorite.getCreateTime());
        return favoriteResponse;
    }
}
