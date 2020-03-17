package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectScenicSpotAddressRequest implements Serializable {

    private static final long serialVersionUID = -7466426833897787177L;

    private Integer scenicSpotAddress;

    private Integer pageNum = 1;
}
