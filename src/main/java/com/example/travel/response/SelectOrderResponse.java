package com.example.travel.response;

import lombok.Data;

@Data
public class SelectOrderResponse {

    private Long id;

    private String img;

    private String ticketName;

    private String ticketDescribe;

    private Integer adultNumber;

    private Integer childrenNumber;

    private Double totalMoney;

    private Integer total;
}
