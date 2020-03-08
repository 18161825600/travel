package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserBaseInfoRequest implements Serializable {

    private static final long serialVersionUID = 7045764094460494543L;

    private Long id;

    private String imgUrl;

    private String nickName;
}
