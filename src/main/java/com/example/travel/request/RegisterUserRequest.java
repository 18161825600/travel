package com.example.travel.request;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String userName;

    private String password;

    private String nickName;

    private String phoneNumber;
}
