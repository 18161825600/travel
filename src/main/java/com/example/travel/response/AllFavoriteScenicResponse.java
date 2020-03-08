package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllFavoriteScenicResponse implements Serializable {

    private static final long serialVersionUID = 1032580125753863215L;

    private List<FavoriteScenicResponse> favoriteScenicResponseList;

    private Integer total;
}
