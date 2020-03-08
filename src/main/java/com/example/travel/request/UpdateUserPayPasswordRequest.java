package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserPayPasswordRequest implements Serializable {

    private static final long serialVersionUID = -3708846266057051118L;

    private Long id;

    private String paymentPassword;
}
