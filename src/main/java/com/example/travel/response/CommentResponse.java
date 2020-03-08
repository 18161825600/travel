package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentResponse implements Serializable {

    private static final long serialVersionUID = -4656255952613699997L;

    private String nickName;

    private String scenicSpotName;

    private String comment;

    private Date createTime;

}
