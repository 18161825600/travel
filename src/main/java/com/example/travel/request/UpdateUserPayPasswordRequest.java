package com.example.travel.request;

import lombok.Data;

@Data
public class UpdateUserPayPasswordRequest {

    private Long id;

    private String paymentPassword;
}
