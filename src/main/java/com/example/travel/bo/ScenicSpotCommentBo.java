package com.example.travel.bo;

import lombok.Data;

import java.util.Date;

@Data
public class ScenicSpotCommentBo {

    private Integer number;

    private String nickName;

    private String imgUrl;

    private String comment;

    private Date createTime;
}
