package com.example.travel.request;

import lombok.Data;

@Data
public class CashOutUserMoneyRequest {

    private Long id;

    private Double money;

    private String paymentPassword;
}
