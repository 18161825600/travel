package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ScenicSpotResponse implements Serializable {

    private static final long serialVersionUID = 4664059150105746240L;

    private Long id;

    private String scenicSpotName;

    private String scenicSpotDescribe;

    private String img;

}
