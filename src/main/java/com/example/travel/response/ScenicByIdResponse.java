package com.example.travel.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ScenicByIdResponse {

    private String scenicSpotName;

    private String scenicSpotAddress;

    private String scenicSpotDescribe;

    private String scenicSpotSynopsis;

    private String img;

    private String imgs;

    private BigDecimal sLongitude;

    private BigDecimal sDimension;

    private String ticketName;

    private String ticketDescribe;

    private Double adultTicketPrice;

    private Double childrenTicketPrice;

    private Integer adultNumber;

    private Integer childrenNumber;
}
