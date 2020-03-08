package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignUserRequest implements Serializable {

    private static final long serialVersionUID = -8305938271596135244L;

    private String userName;

    private String password;
}
