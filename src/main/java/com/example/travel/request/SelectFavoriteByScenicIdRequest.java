package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectFavoriteByScenicIdRequest implements Serializable {

    private static final long serialVersionUID = -818286806477204924L;

    private Long scenicSpotId;

    private Integer pageNum = 1;
}
