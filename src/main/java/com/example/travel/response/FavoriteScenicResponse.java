package com.example.travel.response;

import lombok.Data;

import java.util.Date;

@Data
public class FavoriteScenicResponse {

    private String nickName;

    private Date createTime;

    private Integer total;
}
