package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AddPaymentOrderRequest implements Serializable {
    private static final long serialVersionUID = 3832062732601492133L;

    private List<AddShoppingCarRequest> requestList;
}
