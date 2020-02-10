package com.example.travel.request;

import lombok.Data;

@Data
public class UpdateUserBaseInfoRequest {

    private Long id;

    private String imgUrl;

    private String nickName;
}
