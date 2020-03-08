package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class AddScenicSpotRequest implements Serializable {

    private static final long serialVersionUID = -5164377206875634457L;

    private String scenicSpotName;

    private String scenicSpotAddress;

    private String scenicSpotDescribe;

    private String scenicSpotSynopsis;

    private String scenicSpotTypes;

    private String img;

    private List<String> imgs;

    private BigDecimal sLongitude;

    private BigDecimal sDimension;
}
