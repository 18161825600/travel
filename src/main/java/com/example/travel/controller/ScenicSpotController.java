package com.example.travel.controller;

import com.example.travel.common.TravelJsonResult;
import com.example.travel.request.AddScenicSpotRequest;
import com.example.travel.request.UpdateScenicRequest;
import com.example.travel.response.ChannelDimensionResponse;
import com.example.travel.response.ScenicByIdResponse;
import com.example.travel.response.ScenicSpotResponse;
import com.example.travel.service.ScenicSpotService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;

    @PostMapping(value = "insert/scenicSpot")
    public TravelJsonResult<String> insertScenicSpot(@RequestBody AddScenicSpotRequest addScenicSpotRequest){
        Integer integer = scenicSpotService.insertScenicSpot(addScenicSpotRequest);
        if(integer==1){
            return TravelJsonResult.ok("添加成功");
        }else return TravelJsonResult.errorMsg("添加失败");
    }

    @DeleteMapping(value = "delete/scenicSpot")
    public TravelJsonResult<String> deleteScenicSpot( Long id){
        Integer integer = scenicSpotService.deleteScenicSpot(id);
        if(integer==1){
            return TravelJsonResult.ok("删除成功");
        }else return TravelJsonResult.errorMsg("删除失败");
    }

    @PutMapping(value = "update/scenicSpot")
    public TravelJsonResult<String> updateScenicSpot(@RequestBody UpdateScenicRequest updateScenicRequest){
        Integer integer = scenicSpotService.updateScenicSpot(updateScenicRequest);
        if(integer==1) {
            return TravelJsonResult.ok("修改成功");
        }else return TravelJsonResult.errorMsg("修改失败");
    }

    @GetMapping(value = "select/scenicSpot/by/id")
    public TravelJsonResult<ScenicByIdResponse> selectScenicSpotById( Long id){
        return TravelJsonResult.ok(scenicSpotService.selectScenicSpotById(id));
    }

    @GetMapping(value = "select/channel/dimension")
    public TravelJsonResult<ChannelDimensionResponse> selectChannelDimensionById( Long id){
        return TravelJsonResult.ok(scenicSpotService.selectChannelDimensionById(id));
    }

    @GetMapping(value = "select/all/scenicSpot")
    public TravelJsonResult<PageInfo<ScenicSpotResponse>> selectAllScenicSpot(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(scenicSpotService.selectAllScenicSpot(pageNum));
    }

    @GetMapping(value = "select/some/by/scenicSpotName")
    public TravelJsonResult<PageInfo<ScenicSpotResponse>> selectSomeByScenicSpotName(String scenicSpotName,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(scenicSpotService.selectSomeByScenicSpotName(scenicSpotName, pageNum));
    }

    @GetMapping(value = "select/some/by/scenicSpotAddress")
    public TravelJsonResult<PageInfo<ScenicSpotResponse>> selectSomeByScenicSpotAddress( String scenicSpotAddress,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        return TravelJsonResult.ok(scenicSpotService.selectSomeByScenicSpotAddress(scenicSpotAddress, pageNum));
    }
}
