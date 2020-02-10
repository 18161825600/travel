package com.example.travel.request;

import lombok.Data;

@Data
public class AddCommentRequest {

    private Long userId;

    private Long scenicSpotId;

    private String comment;
}
