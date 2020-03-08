package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddTicketRequest implements Serializable {

    private static final long serialVersionUID = -2289946546573986214L;

    private String scenicSpotName;

    private String ticketName;

    private String ticketDescribe;

    private Double adultTicketPrice;

    private Double childrenTicketPrice;

    private Integer adultNumber;

    private Integer childrenNumber;
}
