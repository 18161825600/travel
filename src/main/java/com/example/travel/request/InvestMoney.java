package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class InvestMoney implements Serializable {

    private static final long serialVersionUID = 5363636357958057834L;

    private Long userId;

    private Double money;

}
