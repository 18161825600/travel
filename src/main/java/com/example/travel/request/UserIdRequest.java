package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserIdRequest implements Serializable {

    private static final long serialVersionUID = 5174338511724065199L;

    private Long userId;

    private Integer pageNum =1;
}
