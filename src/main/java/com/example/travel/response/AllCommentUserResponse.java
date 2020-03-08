package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllCommentUserResponse implements Serializable {

    private static final long serialVersionUID = 6849654496158590736L;

    private List<CommentUserResponse> commentUserResponseList;

    private Integer total;
}
