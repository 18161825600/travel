package com.example.travel.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AddScenicSpotRequest {

    private String scenicSpotName;

    private String scenicSpotAddress;

    private String scenicSpotDescribe;

    private String scenicSpotSynopsis;

    private String img;

    private List<String> imgs;

    private BigDecimal sLongitude;

    private BigDecimal sDimension;
}
