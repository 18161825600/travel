package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddShoppingCarRequest implements Serializable {

    private static final long serialVersionUID = -5584432894265402036L;

    private Long userId;

    private Long ticketId;

    private String spec;

    private Integer number;
}
