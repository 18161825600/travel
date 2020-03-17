package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentScenicResponse implements Serializable {

    private static final long serialVersionUID = -8955265690573502067L;

    private Long commentId;

    private Integer number;

    private String nickName;

    private Long userId;

    private String imgUrl;

    private String comment;

    private String createTime;

}
