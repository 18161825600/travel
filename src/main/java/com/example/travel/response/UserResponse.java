package com.example.travel.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {

    private String userName;

    private String nickName;

    private String phoneNumber;

    private String imgUrl;

    private Date createTime;

    private Integer total;
}
