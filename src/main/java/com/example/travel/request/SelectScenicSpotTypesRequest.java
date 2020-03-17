package com.example.travel.request;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class SelectScenicSpotTypesRequest implements Serializable {

    private static final long serialVersionUID = -2217387974557057881L;

    private Integer scenicSpotTypes;

    private Integer pageNum = 1;
}
