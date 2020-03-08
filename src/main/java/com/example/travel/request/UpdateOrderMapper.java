package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateOrderMapper implements Serializable {
    private static final long serialVersionUID = 5556333882803156982L;

    private List<Long> ids;

    private Short orderState;
}
