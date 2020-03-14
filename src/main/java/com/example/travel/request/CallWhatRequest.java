package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CallWhatRequest implements Serializable {
    private static final long serialVersionUID = -1643521095638697515L;

    private Long userId;

    private Long data;
}
