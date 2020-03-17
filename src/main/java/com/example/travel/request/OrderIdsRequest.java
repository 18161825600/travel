package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderIdsRequest implements Serializable {

    private List<Long> ids;
}
