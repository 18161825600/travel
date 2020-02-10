package com.example.travel.service.impl;

import com.example.travel.dao.ScenicSpotDao;
import com.example.travel.dao.TicketDao;
import com.example.travel.pojo.ScenicSpot;
import com.example.travel.pojo.Ticket;
import com.example.travel.request.AddScenicSpotRequest;
import com.example.travel.request.UpdateScenicRequest;
import com.example.travel.response.ChannelDimensionResponse;
import com.example.travel.response.ScenicByIdResponse;
import com.example.travel.response.ScenicSpotResponse;
import com.example.travel.service.ScenicSpotService;
import com.example.travel.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class ScenicSpotServiceImpl implements ScenicSpotService {

    @Autowired
    private ScenicSpotDao scenicSpotDao;
    @Autowired
    private TicketDao ticketDao;

    @Override
    public Integer insertScenicSpot(AddScenicSpotRequest addScenicSpotRequest) {
        ScenicSpot scenicSpot = new ScenicSpot();
        BeanUtils.copyProperties(addScenicSpotRequest,scenicSpot);
        scenicSpot.setCreateTime(new Date());
        List<String> imgs = addScenicSpotRequest.getImgs();
        if(!CollectionUtils.isEmpty(imgs)){
            HashMap<String,String> hashMap = new HashMap<>();
            for(String img :imgs){
                String uuid = UUID.randomUUID().toString();
                hashMap.put(uuid,img);
            }
            scenicSpot.setImgs(JsonUtils.objectToJson(hashMap));
        }
        return scenicSpotDao.insertScenicSpot(scenicSpot);
    }

    @Override
    public Integer deleteScenicSpot(Long id) {
        return scenicSpotDao.deleteScenicSpot(id);
    }

    @Override
    public Integer updateScenicSpot(UpdateScenicRequest updateScenicRequest) {
        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(updateScenicRequest.getId());
        BeanUtils.copyProperties(updateScenicRequest,scenicSpot);
        scenicSpot.setUpdateTime(new Date());
        List<String> imgs = updateScenicRequest.getImgs();
        if(!CollectionUtils.isEmpty(imgs)){
            HashMap<String,String> hashMap = new HashMap<>();
            for(String img :imgs){
                String uuid = UUID.randomUUID().toString();
                hashMap.put(uuid,img);
            }
            scenicSpot.setImgs(JsonUtils.objectToJson(hashMap));
        }
        return scenicSpotDao.updateScenicSpot(scenicSpot);
    }

    @Override
    public ScenicByIdResponse selectScenicSpotById(Long id) {
        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(id);
        ScenicByIdResponse scenicByIdResponse = new ScenicByIdResponse();
        BeanUtils.copyProperties(scenicSpot,scenicByIdResponse);

        Ticket ticket = ticketDao.selectTicketByScenicId(id);
        scenicByIdResponse.setAdultNumber(ticket.getAdultNumber());
        scenicByIdResponse.setAdultTicketPrice(ticket.getAdultTicketPrice());
        scenicByIdResponse.setChildrenNumber(ticket.getChildrenNumber());
        scenicByIdResponse.setChildrenTicketPrice(ticket.getChildrenTicketPrice());
        scenicByIdResponse.setTicketName(ticket.getTicketName());
        scenicByIdResponse.setTicketDescribe(ticket.getTicketDescribe());

        return scenicByIdResponse;
    }

    @Override
    public ChannelDimensionResponse selectChannelDimensionById(Long id) {
        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(id);
        ChannelDimensionResponse channelDimensionResponse = new ChannelDimensionResponse();
        channelDimensionResponse.setSLongitude(scenicSpot.getsLongitude());
        channelDimensionResponse.setSDimension(scenicSpot.getsDimension());
        return channelDimensionResponse;
    }

    @Override
    public PageInfo<ScenicSpotResponse> selectAllScenicSpot(Integer pageNum) {
        List<ScenicSpot> scenicSpots = scenicSpotDao.selectAllScenicSpot();
        PageHelper.startPage(pageNum,10);
        List<ScenicSpotResponse> list = new ArrayList<>();
        for(ScenicSpot scenicSpot : scenicSpots){
            ScenicSpotResponse scenicSpotResponse = new ScenicSpotResponse();
            BeanUtils.copyProperties(scenicSpot,scenicSpotResponse);
            scenicSpotResponse.setTotal(scenicSpotDao.countAllScenicSpot());
            list.add(scenicSpotResponse);
        }
        PageInfo<ScenicSpotResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<ScenicSpotResponse> selectSomeByScenicSpotName(String scenicSpotName,Integer pageNum) {
        List<ScenicSpot> scenicSpots = scenicSpotDao.selectSomeByScenicSpotName(scenicSpotName);
        PageHelper.startPage(pageNum,10);
        List<ScenicSpotResponse> list = new ArrayList<>();
        for(ScenicSpot scenicSpot : scenicSpots){
            ScenicSpotResponse scenicSpotResponse = new ScenicSpotResponse();
            BeanUtils.copyProperties(scenicSpot,scenicSpotResponse);
            scenicSpotResponse.setTotal(scenicSpotDao.countByScenicSpotName(scenicSpotName));
            list.add(scenicSpotResponse);
        }
        PageInfo<ScenicSpotResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<ScenicSpotResponse> selectSomeByScenicSpotAddress(String scenicSpotAddress, Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<ScenicSpot> scenicSpots = scenicSpotDao.selectSomeByScenicSpotAddress(scenicSpotAddress);
        List<ScenicSpotResponse> list = new ArrayList<>();
        for(ScenicSpot scenicSpot : scenicSpots){
            ScenicSpotResponse scenicSpotResponse = new ScenicSpotResponse();
            BeanUtils.copyProperties(scenicSpot,scenicSpotResponse);
            scenicSpotResponse.setTotal(scenicSpotDao.countByScenicSpotAddress(scenicSpotAddress));
            list.add(scenicSpotResponse);
        }
        PageInfo<ScenicSpotResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


}
