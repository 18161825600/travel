package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddCommentRequest implements Serializable {

    private static final long serialVersionUID = 8872234390781522404L;

    private Long userId;

    private Long scenicSpotId;

    private String comment;
}
