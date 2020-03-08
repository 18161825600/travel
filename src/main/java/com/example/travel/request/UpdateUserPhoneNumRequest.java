package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserPhoneNumRequest implements Serializable {

    private static final long serialVersionUID = -5085389735536736976L;

    private Long id;

    private String phoneNumber;

    private String password;
}
