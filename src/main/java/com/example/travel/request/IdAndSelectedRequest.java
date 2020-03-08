package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class IdAndSelectedRequest implements Serializable {
    private static final long serialVersionUID = 4627795990912574247L;

    private Long id;

    private final Boolean selected = false;
}
