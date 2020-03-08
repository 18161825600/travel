package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllTicketResponse implements Serializable {

    private static final long serialVersionUID = -6909914032346920052L;

    private List<TicketResponse> ticketResponses;

    private Integer total;
}
