package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectOrderResponse implements Serializable {

    private static final long serialVersionUID = 8901361686209087136L;

    private Long id;

    private Long scenicSpotId;

    private String img;

    private String ticketName;

    private String ticketDescribe;

    private String spec;

    private Integer number;

    private Double totalMoney;

    private final Boolean selected = Boolean.FALSE;

}
