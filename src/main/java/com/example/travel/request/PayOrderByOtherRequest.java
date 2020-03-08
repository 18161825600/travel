package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PayOrderByOtherRequest implements Serializable {
    private static final long serialVersionUID = 8520152617409285924L;

    private List<Long> ids;

    private Double totalMoney;
}
