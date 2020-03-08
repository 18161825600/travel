package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateCommentRequest implements Serializable {

    private static final long serialVersionUID = -6277908608197635780L;

    private Long id;

    private String comment;
}
