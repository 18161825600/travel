package com.example.travel.mapper;

import com.example.travel.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketMapper extends CommonMapper<Ticket> {
}