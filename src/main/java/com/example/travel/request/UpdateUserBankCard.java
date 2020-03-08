package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserBankCard implements Serializable {

    private static final long serialVersionUID = 6881780467679706636L;

    private Long id;

    private String paymentPassword;

    private String bankCard;
}
