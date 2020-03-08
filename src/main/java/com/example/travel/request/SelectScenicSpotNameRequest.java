package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectScenicSpotNameRequest implements Serializable {

    private static final long serialVersionUID = -3783693236488613235L;

    private String scenicSpotName;

    private Integer pageNum = 1;
}
