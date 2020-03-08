package com.example.travel.service;

import com.example.travel.request.AddTicketRequest;
import com.example.travel.request.IdRequest;
import com.example.travel.request.PageNumRequest;
import com.example.travel.request.UpdateTicketRequest;
import com.example.travel.response.AllTicketResponse;
import com.example.travel.response.TicketResponse;
import com.github.pagehelper.PageInfo;


public interface TicketService {

    Integer insertTicket(AddTicketRequest addTicketRequest);

    Integer deleteTicket(IdRequest idRequest);

    Integer updateTicket(UpdateTicketRequest updateTicketRequest);

    TicketResponse selectTicketById(IdRequest idRequest);

    AllTicketResponse selectAllTicket(PageNumRequest pageNumRequest);

}
