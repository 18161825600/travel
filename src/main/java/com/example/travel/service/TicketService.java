package com.example.travel.service;

import com.example.travel.request.AddTicketRequest;
import com.example.travel.request.UpdateTicketRequest;
import com.example.travel.response.TicketResponse;
import com.github.pagehelper.PageInfo;


public interface TicketService {

    Integer insertTicket(AddTicketRequest addTicketRequest);

    Integer deleteTicket(Long id);

    Integer updateTicket(UpdateTicketRequest updateTicketRequest);

    TicketResponse selectTicketById(Long id);

    PageInfo<TicketResponse> selectAllTicket(Integer pageNum);

}
