package com.example.travel.mapper;

import com.example.travel.pojo.Order;
import com.example.travel.request.UpdateOrderMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends CommonMapper<Order> {


    Integer payOrderByIds(List<Order> orders);
}