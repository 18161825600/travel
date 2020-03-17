package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RemoveCommentRequest implements Serializable {
    private static final long serialVersionUID = 6237648585687203629L;

    private Long commentId;


}
