package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllScenicSpotResponse  implements Serializable {

    private static final long serialVersionUID = 6887235527948672496L;

    private List<ScenicSpotResponse> scenicSpotResponses;

    private Integer total;
}
