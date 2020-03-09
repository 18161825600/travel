package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddOrPayOrderRequest<T> implements Serializable {
    private static final long serialVersionUID = 4257091683008391832L;

    private Integer type;

    private AddShoppingCarRequest addShoppingCarRequest;

    private PayOrderRequest payOrderRequest;
}
