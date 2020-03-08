package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddOrderRequest implements Serializable {

    private static final long serialVersionUID = 8115484208935616270L;

    private Long userId;

    private Long ticketId;

    private Integer adultNumber;

    private Integer childrenNumber;

}
