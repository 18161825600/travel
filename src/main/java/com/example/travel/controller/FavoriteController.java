package com.example.travel.controller;

import com.example.travel.common.TravelJsonResult;
import com.example.travel.request.*;
import com.example.travel.response.*;
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
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @DeleteMapping(value = "delete/favorite")
    public TravelJsonResult<String> deleteFavorite(@RequestBody IdsRequest idsRequest){
        return TravelJsonResult.ok("删除了"+favoriteService.deleteFavorite(idsRequest)+"条记录");
    }

    @PostMapping(value = "select/favorite/byId")
    public TravelJsonResult<FavoriteResponse> selectFavoriteById(@RequestBody IdRequest idRequest){
        return TravelJsonResult.ok(favoriteService.selectFavoriteById(idRequest));
    }

    @PostMapping(value = "select/all/favorite")
    public TravelJsonResult<AllFavoriteResponse> selectAllFavorite(@RequestBody PageNumRequest pageNumRequest){
        return TravelJsonResult.ok(favoriteService.selectAllFavorite(pageNumRequest));
    }

    @PostMapping(value = "select/favorite/by/userId")
    public TravelJsonResult<AllFavoriteUserResponse> selectFavoriteByUserId(@RequestBody SelectFavoriteByUserIdRequest selectFavoriteByUserIdRequest){
        return TravelJsonResult.ok(favoriteService.selectFavoriteByUserId(selectFavoriteByUserIdRequest));
    }

    @PostMapping(value = "select/favorite/by/scenicId")
    public TravelJsonResult<AllFavoriteScenicResponse> selectFavoriteByScenicId(@RequestBody SelectFavoriteByScenicIdRequest selectFavoriteByScenicIdRequest){
        return TravelJsonResult.ok(favoriteService.selectFavoriteByScenicId(selectFavoriteByScenicIdRequest));
    }
}
