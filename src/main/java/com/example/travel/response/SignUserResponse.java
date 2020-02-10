package com.example.travel.response;

import lombok.Data;

@Data
public class SignUserResponse {

    private Long id;

    private String userName;

    private String password;
}
