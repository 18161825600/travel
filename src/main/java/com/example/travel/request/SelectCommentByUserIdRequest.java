package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectCommentByUserIdRequest implements Serializable {

    private static final long serialVersionUID = -7456237518044874405L;

    private Long userId;

    private Integer pageNum=1;
}
