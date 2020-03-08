package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;


@Data
public class TicketResponse implements Serializable {

    private static final long serialVersionUID = 7030788358523363284L;

    private String ticketName;

    private String ticketDescribe;

    private Double adultTicketPrice;

    private Double childrenTicketPrice;

}
