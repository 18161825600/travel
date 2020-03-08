package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectScenicSpotTypesRequest implements Serializable {

    private static final long serialVersionUID = -2217387974557057881L;

    private String scenicSpotTypes;

    private Integer pageNum = 1;
}
