package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateTicketRequest implements Serializable {

    private static final long serialVersionUID = -2443930258607359317L;

    private Long id;

    private Double adultTicketPrice;

    private Double childrenTicketPrice;

    private Integer adultNumber;

    private Integer childrenNumber;
}
