package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageNumRequest implements Serializable {

    private static final long serialVersionUID = 6333323277601114309L;

    private Integer pageNum=1;
}
