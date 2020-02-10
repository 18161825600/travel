package com.example.travel.request;

import lombok.Data;

@Data
public class AddFavoriteRequest {

    private Long userId;

    private Long scenicSpotId;
}
