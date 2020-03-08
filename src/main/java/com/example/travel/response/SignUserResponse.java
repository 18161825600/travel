package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SignUserResponse implements Serializable {

    private static final long serialVersionUID = 4607051878793421914L;

    private Long id;

    private String userName;

    private String password;

    private String nickName;

    private String imgUrl;

    private String phoneNumber;

    private Double lastMoney;

    private String paymentPassword;

    private String bankCard;

    private Integer favoriteTotal;

    private Integer commentTotal;

    private List<ShoppingCarResponse> shoppingCarResponseList;

    private Integer shoppingCarTotal;
}
