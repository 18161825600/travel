package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserResponse implements Serializable {

    private static final long serialVersionUID = -6260664663856858168L;

    private String userName;

    private String nickName;

    private String phoneNumber;

    private String imgUrl;

    private Date createTime;

    private Integer total;
}
