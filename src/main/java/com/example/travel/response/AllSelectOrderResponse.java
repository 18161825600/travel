package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllSelectOrderResponse implements Serializable {

    private static final long serialVersionUID = 9191652674854974749L;

    private List<SelectOrderResponse> selectOrderResponseList;

    private Integer total;
}
