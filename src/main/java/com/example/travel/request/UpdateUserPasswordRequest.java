package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserPasswordRequest implements Serializable {

    private static final long serialVersionUID = 1597191725065025191L;

    private Long id;

    private String password;

}
