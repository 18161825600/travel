package com.example.travel.request;

import lombok.Data;

@Data
public class UpdateTicketRequest {

    private Long id;

    private Double adultTicketPrice;

    private Double childrenTicketPrice;

    private Integer adultNumber;

    private Integer childrenNumber;
}
