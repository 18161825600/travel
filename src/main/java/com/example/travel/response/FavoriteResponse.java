package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FavoriteResponse implements Serializable {

    private static final long serialVersionUID = 8713566392627070990L;

    private Long id;

    private String scenicSpotName;

    private String scenicSpotAddress;

    private String scenicSpotSynopsis;

    private String img;

    private String nickName;

    private Date createTime;

}
