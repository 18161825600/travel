package com.example.travel.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectOrderByTicketIdRequest implements Serializable {

    private static final long serialVersionUID = -3919911553238242562L;

    private Long ticketId;

    private Integer pageNum = 1;
}
