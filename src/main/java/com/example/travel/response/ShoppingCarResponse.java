package com.example.travel.response;

import lombok.Data;

@Data
public class ShoppingCarResponse {

    private Long orderId;

    private String ticketName;

    private Integer adultNumber;

    private Integer childrenNumber;

    private Double adultTicketPrice;

    private Double childrenTicketPrice;

    private Double totalMoney;
}
