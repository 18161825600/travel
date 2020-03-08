package com.example.travel.service;


import com.example.travel.request.*;
import com.example.travel.response.*;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FavoriteService {

    Integer insertFavorite(AddFavoriteRequest addFavoriteRequest);

    Integer deleteFavorite(IdsRequest idsRequest);

    FavoriteResponse selectFavoriteById(IdRequest idRequest);

    AllFavoriteResponse selectAllFavorite(PageNumRequest pageNumRequest);

    AllFavoriteUserResponse selectFavoriteByUserId(SelectFavoriteByUserIdRequest selectFavoriteByUserIdRequest);

    AllFavoriteScenicResponse selectFavoriteByScenicId(SelectFavoriteByScenicIdRequest selectFavoriteByScenicIdRequest);
}
