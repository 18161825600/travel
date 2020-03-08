package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ChannelDimensionResponse implements Serializable {

    private static final long serialVersionUID = -1634315824240144141L;

    private String scenicSpotName;

    private String img;

    private BigDecimal sLongitude;

    private BigDecimal sDimension;
}
