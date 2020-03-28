package com.example.travel.response;

import com.example.travel.bo.ScenicSpotCommentBo;
import com.example.travel.bo.ScenicSpotImgsListBo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class FreeScenicByIdResponse implements Serializable {

    private static final long serialVersionUID = 2116250565229026903L;

    private String scenicSpotName;

    private String scenicSpotAddress;

    private String scenicSpotDescribe;

    private String scenicSpotSynopsis;

    private String img;

    private List<ScenicSpotImgsListBo> scenicSpotImgsListBos;

    private BigDecimal sLongitude;

    private BigDecimal sDimension;

    private Double adultTicketPrice;

    private Double childrenTicketPrice;

    private String ticketDescribe;

    private List<ScenicSpotCommentBo> scenicSpotCommentBos;
}
