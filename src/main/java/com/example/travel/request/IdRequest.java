package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;


@Data
public class IdRequest implements Serializable {

    private static final long serialVersionUID = -8749149670335620438L;

    private Long id;
}
