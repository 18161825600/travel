package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PayOrderRequest implements Serializable {
    private static final long serialVersionUID = -812733428176462816L;

    private List<Long> ids;

    private Long userId;

    private Double totalMoney;
}
