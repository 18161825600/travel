package com.example.travel.response;

import com.example.travel.bo.ScenicSpotCommentBo;
import com.example.travel.bo.ScenicSpotImgsListBo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ScenicByIdResponse implements Serializable {

    private static final long serialVersionUID = 8977815693938655870L;

    private String scenicSpotName;

    private String scenicSpotAddress;

    private String scenicSpotDescribe;

    private String scenicSpotSynopsis;

    private String img;

    private List<ScenicSpotImgsListBo> scenicSpotImgsListBos;

    private BigDecimal sLongitude;

    private BigDecimal sDimension;

    private String ticketName;

    private String ticketDescribe;

    private Double adultTicketPrice;

    private Double childrenTicketPrice;

    private Integer adultNumber;

    private Integer childrenNumber;

    private List<ScenicSpotCommentBo> scenicSpotCommentBos;
}
