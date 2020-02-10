package com.example.travel.response;

import lombok.Data;

import java.util.Date;

@Data
public class CommentScenicResponse {

    private String nickName;

    private String comment;

    private Date createTime;

    private Integer total;
}
