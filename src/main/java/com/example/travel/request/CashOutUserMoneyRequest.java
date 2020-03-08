package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CashOutUserMoneyRequest implements Serializable {

    private static final long serialVersionUID = 5495545448668047954L;

    private Long id;

    private Double money;

    private String paymentPassword;
}
