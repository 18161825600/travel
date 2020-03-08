package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectOrderByUserIdRequest implements Serializable {

    private static final long serialVersionUID = -729393239378681415L;

    private Long userId;

    private Integer pageNum = 1;
}
