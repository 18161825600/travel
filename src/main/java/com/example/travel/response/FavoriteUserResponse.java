package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FavoriteUserResponse implements Serializable {

    private static final long serialVersionUID = 1937783373219169620L;

    private Long id;

    private Long scenicSpotId;

    private String scenicSpotName;

    private String scenicSpotDescribe;

    private Double adultTicketPrice;

    private String img;

    private String createTime;

    private Integer favoritePeopleNumber;

}
