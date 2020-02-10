package com.example.travel.request;

import lombok.Data;

@Data
public class UpdateUserPasswordRequest {

    private String userName;

    private String password;

    private String copyPassword;
}
