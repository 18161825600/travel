package com.example.travel.response;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ScenicSpotResponse {

    private Long id;

    private String scenicSpotName;

    private String scenicSpotDescribe;

    private String img;

    private Integer total;
}
