package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectCommentByScenicIdRequest implements Serializable {

    private static final long serialVersionUID = 8893915952728524605L;

    private Long scenicSpotId;

    private Integer pageNum=1;
}
