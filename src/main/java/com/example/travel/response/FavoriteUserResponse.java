package com.example.travel.response;

import lombok.Data;

import java.util.Date;

@Data
public class FavoriteUserResponse {

    private String scenicSpotName;

    private String scenicSpotAddress;

    private String scenicSpotSynopsis;

    private String img;

    private Date createTime;

    private Integer total;
}
