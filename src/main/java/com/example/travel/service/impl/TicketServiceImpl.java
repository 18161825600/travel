package com.example.travel.service.impl;

import com.example.travel.dao.ScenicSpotDao;
import com.example.travel.dao.TicketDao;
import com.example.travel.pojo.ScenicSpot;
import com.example.travel.pojo.Ticket;
import com.example.travel.request.AddTicketRequest;
import com.example.travel.request.IdRequest;
import com.example.travel.request.PageNumRequest;
import com.example.travel.request.UpdateTicketRequest;
import com.example.travel.response.AllTicketResponse;
import com.example.travel.response.TicketResponse;
import com.example.travel.service.TicketService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
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
    public Integer deleteTicket(IdRequest idRequest) {
        return ticketDao.deleteTicket(idRequest.getId());
    }

    @Override
    public Integer updateTicket(UpdateTicketRequest updateTicketRequest) {
        Ticket ticket = ticketDao.selectTicketById(updateTicketRequest.getId());
        BeanUtils.copyProperties(updateTicketRequest,ticket);
        ticket.setUpdateTime(new Date());
        return ticketDao.updateTicket(ticket);
    }

    @Override
    public TicketResponse selectTicketById(IdRequest idRequest) {
        Ticket ticket = ticketDao.selectTicketById(idRequest.getId());
        TicketResponse ticketResponse = changeTicketResponse(ticket);
        return ticketResponse;
    }


    @Override
    public AllTicketResponse selectAllTicket(PageNumRequest pageNumRequest) {
        PageHelper.startPage(1,pageNumRequest.getPageNum()*10);
        List<Ticket> tickets = ticketDao.selectAllTicket();
        PageInfo<Ticket> pageInfo = new PageInfo<>(tickets);

        List<Ticket> ticketList = pageInfo.getList();
        AllTicketResponse allTicketResponse = new AllTicketResponse();
        List<TicketResponse> list = new ArrayList<>();
        for(Ticket ticket : ticketList){
            TicketResponse ticketResponse = new TicketResponse();
            BeanUtils.copyProperties(ticket,ticketResponse);
            list.add(ticketResponse);
        }
        allTicketResponse.setTicketResponses(list);
        allTicketResponse.setTotal(ticketDao.countAllTicket());
        return allTicketResponse;
    }

    private TicketResponse changeTicketResponse(Ticket ticket){
        TicketResponse ticketResponse = new TicketResponse();
        BeanUtils.copyProperties(ticket,ticketResponse);
        return ticketResponse;
    }
}
