package com.example.travel.request;

import lombok.Data;

@Data
public class AddOrderRequest {

    private Long userId;

    private Long ticketId;

    private Integer adultNumber;

    private Integer childrenNumber;

}
