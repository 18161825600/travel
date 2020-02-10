package com.example.travel.request;

import lombok.Data;

@Data
public class UpdateUserPhoneNumRequest {

    private Long id;

    private String phoneNumber;

    private String password;
}
