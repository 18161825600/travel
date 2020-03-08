package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderResponse implements Serializable {

    private static final long serialVersionUID = 8064642127672951927L;

    private Long id;

    private String nickName;

    private String scenicSpotName;

    private String ticketName;

    private String ticketDescribe;

    private Double adultTicketPrice;

    private Double childrenTicketPrice;

    private Integer adultNumber;

    private Integer childrenNumber;

    private Double totalMoney;

    private String orderState;

    private Date createTime;

}
