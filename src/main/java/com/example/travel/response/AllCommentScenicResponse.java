package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllCommentScenicResponse implements Serializable {

    private static final long serialVersionUID = 6457793360722215447L;

    private List<CommentScenicResponse> commentScenicResponseList;

    private Integer total;
}
