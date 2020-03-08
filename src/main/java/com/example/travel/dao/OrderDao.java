package com.example.travel.dao;

import com.example.travel.mapper.OrderMapper;
import com.example.travel.pojo.Order;
import com.example.travel.request.UpdateOrderMapper;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class OrderDao {

    @Autowired
    private OrderMapper orderMapper;

    public Integer insertOrder(Order order){
        return orderMapper.insert(order);
    }

    public Integer deleteOrder(List<Long> ids){
        Example example = new Example(Order.class);
        example.createCriteria().andIn("id",ids);
        return orderMapper.deleteByExample(example);
    }

    public Integer chargeBackOrder(Order order){
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    public Integer updateOrder(Order order){
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    public Integer payOrder(List<Order> orders ){
        return orderMapper.payOrderByIds(orders);
    }

    public Order selectOrderById(Long id){
        return orderMapper.selectByPrimaryKey(id);
    }

    public List<Order> selectAllOrder(){
        return orderMapper.selectAll();
    }

    public Order selectOrderByUserIdAndTicketIdAndState(Long userId,Long ticketId,Short orderState){
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("userId",userId)
                .andEqualTo("ticketId",ticketId)
                .andEqualTo("orderState",orderState);
        return orderMapper.selectOneByExample(example);
    }

    public Order selectAdultOrdersByUserIdAndTicketIdAndState(Long userId,Long ticketId,Short orderState){
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("userId",userId)
                .andEqualTo("ticketId",ticketId)
                .andEqualTo("orderState",orderState)
                .andGreaterThanOrEqualTo("adultNumber",1);
        return orderMapper.selectOneByExample(example);
    }

    public Order selectStudentOrdersByUserIdAndTicketIdAndState(Long userId,Long ticketId,Short orderState){
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("userId",userId)
                .andEqualTo("ticketId",ticketId)
                .andEqualTo("orderState",orderState)
                .andGreaterThanOrEqualTo("childrenNumber",1);
        return orderMapper.selectOneByExample(example);
    }

    public Order selectOrderByUserIdAndTicketId(Long userId,Long ticketId){
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("userId",userId)
                .andEqualTo("ticketId",ticketId);
        return orderMapper.selectOneByExample(example);
    }

    public List<Order> selectOrderByUserId(Long userId){
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("userId",userId);
        return orderMapper.selectByExample(example);
    }

    public List<Order> selectOrderByTicketId(Long ticketId){
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("ticketId",ticketId);
        return orderMapper.selectByExample(example);
    }

    public List<Order> selectOrderByUserIdAndState(Long userId,Short orderState){
        Example example = new Example(Order.class);
        example.createCriteria()
                .andEqualTo("userId",userId)
                .andEqualTo("orderState",orderState);
        return orderMapper.selectByExample(example);
    }

    public Integer countAllOrder(){
        Example example = new Example(Order.class);
        return orderMapper.selectCountByExample(example);
    }

    public Integer countOrderByUserId(Long userId){
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("userId",userId);
        return orderMapper.selectCountByExample(example);
    }

    public Integer countOrderByTicketId(Long ticketId){
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("ticketId",ticketId);
        return orderMapper.selectCountByExample(example);
    }

    public Integer countOrderByUserIdAndState(Long userId,Short orderState) {
        Example example = new Example(Order.class);
        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("orderState", orderState);
        return orderMapper.selectCountByExample(example);
    }
}
