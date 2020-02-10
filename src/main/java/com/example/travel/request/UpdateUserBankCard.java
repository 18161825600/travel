package com.example.travel.request;

import lombok.Data;

@Data
public class UpdateUserBankCard {

    private Long id;

    private String paymentPassword;

    private String bankCard;
}
