package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentUserResponse implements Serializable {

    private static final long serialVersionUID = 2639090745489176742L;

    private String scenicSpotName;

    private String comment;

    private Date createTime;

}
