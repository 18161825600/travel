package com.example.travel.service;


import com.example.travel.pojo.ScenicSpot;
import com.example.travel.request.*;
import com.example.travel.response.AllScenicSpotResponse;
import com.example.travel.response.ChannelDimensionResponse;
import com.example.travel.response.ScenicByIdResponse;
import com.example.travel.response.ScenicSpotResponse;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface ScenicSpotService {

    Integer insertScenicSpot(AddScenicSpotRequest addScenicSpotRequest);

    Integer deleteScenicSpot(IdRequest idRequest);

    Integer updateScenicSpot(UpdateScenicRequest updateScenicRequest);

    <T>T selectScenicSpotById(Long id);

    ChannelDimensionResponse getChannelDimensionById(IdRequest idRequest);

//    List<ChannelDimensionResponse> selectChannelDimensionById();

    <T>T selectOneLikeScenicSpotName(String scenicSpotName);

    AllScenicSpotResponse selectAllScenicSpot(PageNumRequest pageNumRequest);

    AllScenicSpotResponse selectSomeByScenicSpotName(SelectScenicSpotNameRequest selectScenicSpotNameRequest);

    AllScenicSpotResponse selectSomeByScenicSpotAddress(SelectScenicSpotAddressRequest selectScenicSpotAddressRequest);

    AllScenicSpotResponse selectSomeByScenicSpotTypes(SelectScenicSpotTypesRequest selectScenicSpotTypesRequest);

    AllScenicSpotResponse threeInOne(ThreeInOneRequest request);

}
