package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderIdsResponse implements Serializable {
    private static final long serialVersionUID = -6666873180310780927L;

    private List<Long> ids;
}
