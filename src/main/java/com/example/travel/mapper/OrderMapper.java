package com.example.travel.mapper;

import com.example.travel.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends CommonMapper<Order> {
}