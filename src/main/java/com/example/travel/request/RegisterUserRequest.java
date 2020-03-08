package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterUserRequest implements Serializable {

    private static final long serialVersionUID = 222694515401287810L;

    private String userName;

    private String password;

    private String nickName;

    private String phoneNumber;
}
