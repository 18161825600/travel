package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class GetChannelDimensionById implements Serializable {

    private static final long serialVersionUID = 8793377341848429780L;
    private BigDecimal sLongitude;

    private BigDecimal sDimension;
}
