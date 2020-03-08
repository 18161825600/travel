package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateShoppingCarRequest implements Serializable {
    private static final long serialVersionUID = -1326430427726422509L;

    private Long id;

    private String spec;

    private Integer number;
}
