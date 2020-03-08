package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IdsRequest implements Serializable {

    private static final long serialVersionUID = -7722709314996073644L;

    private List<Long> ids;
}
