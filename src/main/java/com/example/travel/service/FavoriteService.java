package com.example.travel.service;


import com.example.travel.request.AddFavoriteRequest;
import com.example.travel.response.FavoriteResponse;
import com.example.travel.response.FavoriteScenicResponse;
import com.example.travel.response.FavoriteUserResponse;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FavoriteService {

    Integer insertFavorite(AddFavoriteRequest addFavoriteRequest);

    Integer deleteFavorite(List<Long> ids);

    FavoriteResponse selectFavoriteById(Long id);

    PageInfo<FavoriteResponse> selectAllFavorite(Integer pageNum);

    PageInfo<FavoriteUserResponse> selectFavoriteByUserId(Long userId, Integer pageNum);

    PageInfo<FavoriteScenicResponse> selectFavoriteByScenicId(Long scenicSpotId, Integer pageNum);
}
