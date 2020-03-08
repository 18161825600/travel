package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdateScenicRequest implements Serializable {

    private static final long serialVersionUID = -3899525992602786488L;

    private Long id;

    private String scenicSpotName;

    private String scenicSpotAddress;

    private String scenicSpotDescribe;

    private String scenicSpotSynopsis;

    private String img;

    private List<String> imgs;

    private BigDecimal sLongitude;

    private BigDecimal sDimension;
}
