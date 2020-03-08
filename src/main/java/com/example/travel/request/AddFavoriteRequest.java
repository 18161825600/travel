package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddFavoriteRequest implements Serializable {

    private static final long serialVersionUID = 2907340004057565857L;

    private Long userId;

    private Long scenicSpotId;
}
