package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllCommentResponse implements Serializable {

    private static final long serialVersionUID = 1500492754493919854L;

    private List<CommentResponse> commentResponseList;

    private Integer total;
}
