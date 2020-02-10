package com.example.travel.request;

import lombok.Data;

@Data
public class AddTicketRequest {

    private String scenicSpotName;

    private String ticketName;

    private String ticketDescribe;

    private Double adultTicketPrice;

    private Double childrenTicketPrice;

    private Integer adultNumber;

    private Integer childrenNumber;
}
