package com.example.travel.response;

import lombok.Data;


@Data
public class TicketResponse {

    private String ticketName;

    private String ticketDescribe;

    private Double adultTicketPrice;

    private Double childrenTicketPrice;

    private Integer total;
}
