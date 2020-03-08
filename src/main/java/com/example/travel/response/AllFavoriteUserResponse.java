package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllFavoriteUserResponse implements Serializable {

    private static final long serialVersionUID = 1541380592111882054L;

    private List<FavoriteUserResponse> favoriteUserResponseList;

    private Integer total;
}
