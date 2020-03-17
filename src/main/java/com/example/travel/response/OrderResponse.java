package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderResponse implements Serializable {

    private static final long serialVersionUID = 8064642127672951927L;

    private Long id;//订单id

    private Long scenicSpotId;//景点id

    private String nickName;//用户昵称

    private String scenicSpotName;//景点名称

    private String img;//景点图片

    private String ticketName;//门票名称

    private String ticketDescribe;//门票描述

    private Double adultTicketPrice;//成人票价格

    private Double childrenTicketPrice;//儿童票价格

    private Double price;//用户买的票的单价

    private String spec;//规格

    private Integer number;//购买门票数量

    private Double totalMoney;//门票总价

    private String orderState;//门票状态

    private String createTime;//创建订单时间

    private final Boolean selected = Boolean.FALSE;
}
