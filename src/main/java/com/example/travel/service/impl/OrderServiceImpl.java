package com.example.travel.service.impl;

import com.example.travel.dao.OrderDao;
import com.example.travel.dao.ScenicSpotDao;
import com.example.travel.dao.TicketDao;
import com.example.travel.dao.UserDao;
import com.example.travel.pojo.Order;
import com.example.travel.pojo.ScenicSpot;
import com.example.travel.pojo.Ticket;
import com.example.travel.pojo.User;
import com.example.travel.request.AddOrderRequest;
import com.example.travel.response.OrderResponse;
import com.example.travel.response.SelectOrderResponse;
import com.example.travel.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private ScenicSpotDao scenicSpotDao;

    @Override
    public Integer insertOrder(AddOrderRequest addOrderRequest) {
        Order order = new Order();
        BeanUtils.copyProperties(addOrderRequest,order);

        User user = userDao.selectUserById(addOrderRequest.getUserId());
        if(user==null){
            return -3;//用户不存在
        }
        Ticket ticket = ticketDao.selectTicketById(addOrderRequest.getTicketId());
        if(ticket==null){
            return -4;//门票不存在
        }
        if(addOrderRequest.getAdultNumber()==null && addOrderRequest.getChildrenNumber()==null){
            return -1;//门票不能为空
        }else if(addOrderRequest.getAdultNumber()!=null && addOrderRequest.getChildrenNumber()==null){
            order.setTotalMoney(ticket.getAdultTicketPrice()*addOrderRequest.getAdultNumber());
            ticket.setAdultNumber(ticket.getAdultNumber()-addOrderRequest.getAdultNumber());
        }else if(addOrderRequest.getAdultNumber()==null && addOrderRequest.getChildrenNumber()!=null){
            order.setTotalMoney(ticket.getChildrenTicketPrice()*addOrderRequest.getChildrenNumber());
            ticket.setChildrenNumber(ticket.getChildrenNumber()-addOrderRequest.getChildrenNumber());
        }else {
            order.setTotalMoney(ticket.getAdultTicketPrice()*addOrderRequest.getAdultNumber()+ticket.getChildrenTicketPrice()*addOrderRequest.getChildrenNumber());
            ticket.setAdultNumber(ticket.getAdultNumber()-addOrderRequest.getAdultNumber());
            ticket.setChildrenNumber(ticket.getChildrenNumber()-addOrderRequest.getChildrenNumber());
        }
        if(user.getLastMoney()==null){
            order.setOrderState((short)0);
            order.setCreateTime(new Date());
            orderDao.insertOrder(order);
            return 2;//用户余额不足添加成功等待付款
        }else if(user.getLastMoney()<order.getTotalMoney()){
            order.setOrderState((short)0);
            order.setCreateTime(new Date());
            orderDao.insertOrder(order);
            return 2;//用户余额不足添加成功等待付款
        }else {
            ticket.setUpdateTime(new Date());
            ticketDao.updateTicket(ticket);

            user.setLastMoney(user.getLastMoney()-order.getTotalMoney());
            user.setUpdateTime(new Date());
            userDao.updateUser(user);

            order.setOrderState((short)1);
            order.setCreateTime(new Date());
            return orderDao.insertOrder(order);
        }
    }

    @Override
    public Integer insertShoppingCar(AddOrderRequest addOrderRequest) {
        Order order = new Order();
        BeanUtils.copyProperties(addOrderRequest,order);
        order.setOrderState((short)4);
        order.setCreateTime(new Date());

        Ticket ticket = ticketDao.selectTicketById(addOrderRequest.getTicketId());
        order.setTotalMoney(addOrderRequest.getAdultNumber()*ticket.getAdultTicketPrice()+addOrderRequest.getChildrenNumber()*ticket.getChildrenTicketPrice());
        return orderDao.insertOrder(order);
    }

    @Override
    public Integer deleteOrder(List<Long> ids) {
        return orderDao.deleteOrder(ids);
    }

    @Override
    public Integer chargeBackOrder(Long id) {
        Order order = orderDao.selectOrderById(id);
        User user = userDao.selectUserById(order.getUserId());
        Ticket ticket = ticketDao.selectTicketById(order.getTicketId());
        if(order!=null){
            if (order.getOrderState()==0) {
                return -2;//订单未付款
            } else if (order.getOrderState()==1){
                user.setLastMoney(user.getLastMoney()+order.getTotalMoney());
                user.setUpdateTime(new Date());
                userDao.updateUser(user);

                ticket.setChildrenNumber(ticket.getChildrenNumber()+order.getChildrenNumber());
                ticket.setAdultNumber(ticket.getAdultNumber()+order.getAdultNumber());
                ticket.setUpdateTime(new Date());
                ticketDao.updateTicket(ticket);

                order.setOrderState((short)2);
                order.setUpdateTime(new Date());
                return orderDao.chargeBackOrder(order);
            }else if(order.getOrderState()==2){
                return -3;//订单已退款
            }else return -4;//门票过期无法退款
        }else return -1;//订单不存在
    }

    @Override
    public OrderResponse selectOrderById(Long id) {
        Order order = orderDao.selectOrderById(id);
        OrderResponse orderResponse = changeOrderResponse(order);
        orderResponse.setTotal(1);
        return orderResponse;
    }

    @Override
    public PageInfo<OrderResponse> selectAllOrder(Integer pageNum) {
        List<Order> orders = orderDao.selectAllOrder();
        PageHelper.startPage(pageNum,10);
        List<OrderResponse> list = new ArrayList<>();
        for(Order order : orders){
            OrderResponse orderResponse = changeOrderResponse(order);
            orderResponse.setTotal(orderDao.countAllOrder());
            list.add(orderResponse);
        }
        PageInfo<OrderResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<OrderResponse> selectOrderByUserId(Long userId,Integer pageNum) {
        List<Order> orders = orderDao.selectOrderByUserId(userId);
        PageHelper.startPage(pageNum,10);
        List<OrderResponse> list = new ArrayList<>();
        for(Order order : orders){
            OrderResponse orderResponse = changeOrderResponse(order);
            orderResponse.setTotal(orderDao.countOrderByUserId(userId));
            list.add(orderResponse);
        }
        PageInfo<OrderResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<OrderResponse> selectOrderByTicketId(Long ticketId,Integer pageNum) {
        List<Order> orders = orderDao.selectOrderByTicketId(ticketId);
        PageHelper.startPage(pageNum,10);
        List<OrderResponse> list = new ArrayList<>();
        for(Order order : orders){
            OrderResponse orderResponse = changeOrderResponse(order);
            orderResponse.setTotal(orderDao.countOrderByTicketId(ticketId));
            list.add(orderResponse);
        }
        PageInfo<OrderResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<SelectOrderResponse> selectShoppingCar(Long userId) {
        List<Order> orders = orderDao.selectOrderByUserIdAndState(userId, (short) 4);
        List<SelectOrderResponse> list = new ArrayList<>();
        for(Order order : orders){
            SelectOrderResponse selectOrderResponse = changeSelectOrderResponse(order);

            selectOrderResponse.setTotal(orderDao.countOrderByUserIdAndState(userId,(short)4));
            list.add(selectOrderResponse);
        }
        return list;
    }

    @Override
    public List<SelectOrderResponse> selectWaitPayment(Long userId) {
        List<Order> orders = orderDao.selectOrderByUserIdAndState(userId, (short) 0);
        List<SelectOrderResponse> list = new ArrayList<>();
        for(Order order : orders){
            SelectOrderResponse selectOrderResponse = changeSelectOrderResponse(order);
            selectOrderResponse.setTotal(orderDao.countOrderByUserIdAndState(userId,(short)0));
            list.add(selectOrderResponse);
        }
        return list;
    }

    @Override
    public List<SelectOrderResponse> selectChargeBack(Long userId) {
        List<Order> orders = orderDao.selectOrderByUserIdAndState(userId, (short) 2);
        List<SelectOrderResponse> list = new ArrayList<>();
        for(Order order : orders){
            SelectOrderResponse selectOrderResponse = changeSelectOrderResponse(order);
            selectOrderResponse.setTotal(orderDao.countOrderByUserIdAndState(userId,(short)2));
            list.add(selectOrderResponse);
        }
        return list;
    }

    @Override
    public List<SelectOrderResponse> selectSuccessOrder(Long userId) {
        List<Order> orders = orderDao.selectOrderByUserIdAndState(userId, (short) 1);
        List<SelectOrderResponse> list = new ArrayList<>();
        for(Order order : orders){
            SelectOrderResponse selectOrderResponse = changeSelectOrderResponse(order);
            selectOrderResponse.setTotal(orderDao.countOrderByUserIdAndState(userId,(short)1));
            list.add(selectOrderResponse);
        }
        return list;
    }

    public OrderResponse changeOrderResponse(Order order){
        User user = userDao.selectUserById(order.getUserId());
        Ticket ticket = ticketDao.selectTicketById(order.getTicketId());
        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(ticket.getScenicSpotId());
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId(order.getId());
        orderResponse.setNickName(user.getNickName());
        orderResponse.setScenicSpotName(scenicSpot.getScenicSpotName());
        orderResponse.setTicketName(ticket.getTicketName());
        orderResponse.setTicketDescribe(ticket.getTicketDescribe());
        orderResponse.setAdultTicketPrice(ticket.getAdultTicketPrice());
        orderResponse.setChildrenTicketPrice(ticket.getChildrenTicketPrice());
        orderResponse.setAdultNumber(order.getAdultNumber());
        orderResponse.setChildrenNumber(order.getChildrenNumber());
        orderResponse.setTotalMoney(order.getTotalMoney());
        orderResponse.setCreateTime(order.getCreateTime());

        if(order.getOrderState()==0){
            orderResponse.setOrderState("等待付款");
        }else if(order.getOrderState()==1){
            orderResponse.setOrderState("交易成功");
        }else if(order.getOrderState()==2){
            orderResponse.setOrderState("已退款");
        }else if(order.getOrderState()==3){
            orderResponse.setOrderState("门票已过期");
        }else {
            orderResponse.setOrderState("购物车中未付款");
        }

        return orderResponse;
    }

    public SelectOrderResponse changeSelectOrderResponse(Order order){
        SelectOrderResponse shoppingCarResponse = new SelectOrderResponse();
        BeanUtils.copyProperties(order,shoppingCarResponse);

        Ticket ticket = ticketDao.selectTicketById(order.getTicketId());
        shoppingCarResponse.setTicketName(ticket.getTicketName());
        shoppingCarResponse.setTicketDescribe(ticket.getTicketDescribe());

        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(ticket.getScenicSpotId());
        shoppingCarResponse.setImg(scenicSpot.getImg());

        return shoppingCarResponse;
    }
}
