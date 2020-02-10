package com.example.travel.request;

import lombok.Data;

@Data
public class InvestMoney {

    private Long id;

    private Double money;

    private String paymentPassword;
}
