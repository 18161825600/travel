package com.example.travel.controller;

import com.example.travel.common.TravelJsonResult;
import com.example.travel.request.AddFavoriteRequest;
import com.example.travel.response.FavoriteResponse;
import com.example.travel.response.FavoriteScenicResponse;
import com.example.travel.response.FavoriteUserResponse;
import com.example.travel.service.FavoriteService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping(value = "insert/favorite")
    public TravelJsonResult<String> insertFavorite(@RequestBody AddFavoriteRequest addFavoriteRequest){
        Integer integer = favoriteService.insertFavorite(addFavoriteRequest);
        if(integer==1){
            return TravelJsonResult.ok("添加成功");
        }else return TravelJsonResult.errorMsg("添加失败");
    }

    @DeleteMapping(value = "delete/favorite")
    public TravelJsonResult<String> deleteFavorite(@RequestBody List<Long> ids){
        return TravelJsonResult.ok("删除了"+favoriteService.deleteFavorite(ids)+"条记录");
    }

    @GetMapping(value = "select/favorite/byId")
    public TravelJsonResult<FavoriteResponse> selectFavoriteById( Long id){
        return TravelJsonResult.ok(favoriteService.selectFavoriteById(id));
    }

    @GetMapping(value = "select/all/favorite")
    public TravelJsonResult<PageInfo<FavoriteResponse>> selectAllFavorite(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(favoriteService.selectAllFavorite(pageNum));
    }

    @GetMapping(value = "select/favorite/by/userId")
    public TravelJsonResult<PageInfo<FavoriteUserResponse>> selectFavoriteByUserId(Long userId, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(favoriteService.selectFavoriteByUserId(userId, pageNum));
    }

    @GetMapping(value = "select/favorite/by/scenicId")
    public TravelJsonResult<PageInfo<FavoriteScenicResponse>> selectFavoriteByScenicId( Long scenicSpotId, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(favoriteService.selectFavoriteByScenicId(scenicSpotId, pageNum));
    }
}
