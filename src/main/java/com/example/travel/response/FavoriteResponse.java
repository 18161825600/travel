package com.example.travel.response;

import lombok.Data;

import java.util.Date;

@Data
public class FavoriteResponse {

    private Long id;

    private String scenicSpotName;

    private String scenicSpotAddress;

    private String scenicSpotSynopsis;

    private String img;

    private String nickName;

    private Date createTime;

    private Integer total;
}
