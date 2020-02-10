package com.example.travel.service;


import com.example.travel.pojo.ScenicSpot;
import com.example.travel.request.AddScenicSpotRequest;
import com.example.travel.request.UpdateScenicRequest;
import com.example.travel.response.ChannelDimensionResponse;
import com.example.travel.response.ScenicByIdResponse;
import com.example.travel.response.ScenicSpotResponse;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface ScenicSpotService {

    Integer insertScenicSpot(AddScenicSpotRequest addScenicSpotRequest);

    Integer deleteScenicSpot(Long id);

    Integer updateScenicSpot(UpdateScenicRequest updateScenicRequest);

    ScenicByIdResponse selectScenicSpotById(Long id);

    ChannelDimensionResponse selectChannelDimensionById(Long id);

    PageInfo<ScenicSpotResponse> selectAllScenicSpot(Integer pageNum);

    PageInfo<ScenicSpotResponse> selectSomeByScenicSpotName(String scenicSpotName,Integer pageNum);

    PageInfo<ScenicSpotResponse> selectSomeByScenicSpotAddress(String scenicSpotAddress,Integer pageNum);
}
