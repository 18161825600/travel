package com.example.travel.controller;

import com.example.travel.common.TravelJsonResult;
import com.example.travel.pojo.ScenicSpot;
import com.example.travel.request.*;
import com.example.travel.response.AllScenicSpotResponse;
import com.example.travel.response.ChannelDimensionResponse;
import com.example.travel.response.ScenicByIdResponse;
import com.example.travel.response.ScenicSpotResponse;
import com.example.travel.service.ScenicSpotService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;

    @PostMapping(value = "insert/scenicSpot")
    public TravelJsonResult<String> insertScenicSpot(@RequestBody AddScenicSpotRequest addScenicSpotRequest){
        Integer integer = scenicSpotService.insertScenicSpot(addScenicSpotRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @DeleteMapping(value = "delete/scenicSpot")
    public TravelJsonResult<String> deleteScenicSpot(IdRequest idRequest){
        Integer integer = scenicSpotService.deleteScenicSpot(idRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @PutMapping(value = "update/scenicSpot")
    public TravelJsonResult<String> updateScenicSpot(@RequestBody UpdateScenicRequest updateScenicRequest){
        Integer integer = scenicSpotService.updateScenicSpot(updateScenicRequest);
        if(integer==1) {
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @GetMapping(value = "select/scenicSpot/by/id")
    public <T>T selectScenicSpotById(@RequestParam(value = "id") Long id){
        return (T)TravelJsonResult.ok(scenicSpotService.selectScenicSpotById(id));
    }

    @GetMapping(value = "select/channel/dimension")
    public TravelJsonResult<List<ChannelDimensionResponse>> selectChannelDimensionById(){
        return TravelJsonResult.ok(scenicSpotService.selectChannelDimensionById());
    }

    @GetMapping(value = "select/one/like/scenicSpotName")
    public <T>T selectOneLikeScenicSpotName(String scenicSpotName){
        return (T)TravelJsonResult.ok(scenicSpotService.selectOneLikeScenicSpotName(scenicSpotName));
    }

    @PostMapping(value = "select/all/scenicSpot")
    public TravelJsonResult<AllScenicSpotResponse> selectAllScenicSpot(@RequestBody PageNumRequest pageNumRequest){
        return TravelJsonResult.ok(scenicSpotService.selectAllScenicSpot(pageNumRequest));
    }

    @PostMapping(value = "select/some/by/scenicSpotName")
    public TravelJsonResult<AllScenicSpotResponse> selectSomeByScenicSpotName(@RequestBody SelectScenicSpotNameRequest selectScenicSpotNameRequest){
        return TravelJsonResult.ok(scenicSpotService.selectSomeByScenicSpotName(selectScenicSpotNameRequest));
    }

    @PostMapping(value = "select/some/by/scenicSpotAddress")
    public TravelJsonResult<AllScenicSpotResponse> selectSomeByScenicSpotAddress(@RequestBody SelectScenicSpotAddressRequest selectScenicSpotAddressRequest){
        return TravelJsonResult.ok(scenicSpotService.selectSomeByScenicSpotAddress(selectScenicSpotAddressRequest));
    }

    @PostMapping(value = "select/some/by/scenicSpotTypes")
    public TravelJsonResult<AllScenicSpotResponse> selectSomeByScenicSpotTypes(@RequestBody SelectScenicSpotTypesRequest selectScenicSpotTypesRequest){
        return TravelJsonResult.ok(scenicSpotService.selectSomeByScenicSpotTypes(selectScenicSpotTypesRequest));
    }
}
