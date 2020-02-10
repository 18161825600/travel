package com.example.travel.response;

import lombok.Data;

import java.util.Date;

@Data
public class CommentResponse {

    private String nickName;

    private String scenicSpotName;

    private String comment;

    private Date createTime;

    private Integer total;
}
