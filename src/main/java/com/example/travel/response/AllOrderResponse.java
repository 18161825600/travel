package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllOrderResponse implements Serializable {

    private static final long serialVersionUID = 4718184460573148796L;

    private List<OrderResponse> orderResponseList;

    private Integer total;
}
