package com.example.travel.service.impl;

import com.example.travel.dao.ScenicSpotDao;
import com.example.travel.dao.TicketDao;
import com.example.travel.pojo.ScenicSpot;
import com.example.travel.pojo.Ticket;
import com.example.travel.request.AddTicketRequest;
import com.example.travel.request.UpdateTicketRequest;
import com.example.travel.response.TicketResponse;
import com.example.travel.service.TicketService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private ScenicSpotDao scenicSpotDao;

    @Override
    public Integer insertTicket(AddTicketRequest addTicketRequest) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(addTicketRequest,ticket);
        ScenicSpot scenicSpot = scenicSpotDao.selectOneByScenicSpotName(addTicketRequest.getScenicSpotName());
        ticket.setScenicSpotId(scenicSpot.getId());
        ticket.setCreateTime(new Date());
        return ticketDao.insertTicket(ticket);
    }

    @Override
    public Integer deleteTicket(Long id) {
        return ticketDao.deleteTicket(id);
    }

    @Override
    public Integer updateTicket(UpdateTicketRequest updateTicketRequest) {
        Ticket ticket = ticketDao.selectTicketById(updateTicketRequest.getId());
        BeanUtils.copyProperties(updateTicketRequest,ticket);
        ticket.setUpdateTime(new Date());
        return ticketDao.updateTicket(ticket);
    }

    @Override
    public TicketResponse selectTicketById(Long id) {
        Ticket ticket = ticketDao.selectTicketById(id);
        TicketResponse ticketResponse = changeTicketResponse(ticket);
        ticketResponse.setTotal(1);
        return ticketResponse;
    }


    @Override
    public PageInfo<TicketResponse> selectAllTicket(Integer pageNum) {
        List<Ticket> tickets = ticketDao.selectAllTicket();
        PageHelper.startPage(pageNum,10);
        List<TicketResponse> list = new ArrayList<>();
        for(Ticket ticket : tickets){
            TicketResponse ticketResponse = changeTicketResponse(ticket);
            ticketResponse.setTotal(ticketDao.countAllTicket());
            list.add(ticketResponse);
        }
        PageInfo<TicketResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public TicketResponse changeTicketResponse(Ticket ticket){
        TicketResponse ticketResponse = new TicketResponse();
        BeanUtils.copyProperties(ticket,ticketResponse);
        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(ticket.getScenicSpotId());
        return ticketResponse;
    }
}
