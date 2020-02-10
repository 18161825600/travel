package com.example.travel.dao;

import com.example.travel.mapper.TicketMapper;
import com.example.travel.pojo.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class TicketDao {

    @Autowired
    private TicketMapper ticketMapper;

    public Integer insertTicket(Ticket ticket){
        return ticketMapper.insert(ticket);
    }

    public Integer deleteTicket(Long id){
        return ticketMapper.deleteByPrimaryKey(id);
    }

    public Integer updateTicket(Ticket ticket){
        return ticketMapper.updateByPrimaryKeySelective(ticket);
    }

    public Ticket selectTicketById(Long id){
        return ticketMapper.selectByPrimaryKey(id);
    }

    public Ticket selectTicketByScenicId(Long scenicSpotId){
        Example example = new Example(Ticket.class);
        example.createCriteria().andEqualTo("scenicSpotId",scenicSpotId);
        return ticketMapper.selectOneByExample(example);
    }

    public List<Ticket> selectAllTicket(){
        return ticketMapper.selectAll();
    }

    public Integer countAllTicket(){
        Example example = new Example(Ticket.class);
        return ticketMapper.selectCountByExample(example);
    }

}
