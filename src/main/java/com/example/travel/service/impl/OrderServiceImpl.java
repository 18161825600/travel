package com.example.travel.service.impl;

import com.example.travel.dao.OrderDao;
import com.example.travel.dao.ScenicSpotDao;
import com.example.travel.dao.TicketDao;
import com.example.travel.dao.UserDao;
import com.example.travel.pojo.Order;
import com.example.travel.pojo.ScenicSpot;
import com.example.travel.pojo.Ticket;
import com.example.travel.pojo.User;
import com.example.travel.request.*;
import com.example.travel.response.*;
import com.example.travel.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class )
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

            User admin = userDao.selectUserById(1L);
            admin.setLastMoney(admin.getLastMoney()+order.getTotalMoney());
            admin.setUpdateTime(new Date());
            userDao.updateUser(admin);

            order.setOrderState((short)1);
            order.setCreateTime(new Date());
            return orderDao.insertOrder(order);
        }
    }

    @Override
    public Integer insertShoppingCar(AddShoppingCarRequest shoppingCarRequest) {
        Order oldOrder = orderDao.selectOrderByUserIdAndTicketIdAndState(shoppingCarRequest.getUserId(), shoppingCarRequest.getTicketId(), (short) 4);
        Ticket ticket = ticketDao.selectTicketById(shoppingCarRequest.getTicketId());

        if(oldOrder!=null && oldOrder.getAdultNumber()>0 && oldOrder.getChildrenNumber()==0) {
            if(shoppingCarRequest.getSpec().equals("规格:成人")) {
                Order order = orderDao.selectAdultOrdersByUserIdAndTicketIdAndState(shoppingCarRequest.getUserId(), shoppingCarRequest.getTicketId(), (short) 4);

                order.setAdultNumber(order.getAdultNumber() + shoppingCarRequest.getNumber());
                order.setTotalMoney(order.getTotalMoney() + shoppingCarRequest.getNumber() * ticket.getAdultTicketPrice());

                ticket.setAdultNumber(ticket.getAdultNumber() - shoppingCarRequest.getNumber());

                ticket.setUpdateTime(new Date());
                ticketDao.updateTicket(ticket);

                order.setUpdateTime(new Date());
                return orderDao.updateOrder(order);
            }else {
                return addOrderToCar(shoppingCarRequest,ticket);
            }
        }else if(oldOrder!=null && oldOrder.getChildrenNumber()>0 && oldOrder.getAdultNumber()==0){
            if(shoppingCarRequest.getSpec().equals("规格:学生")) {
                Order order = orderDao.selectStudentOrdersByUserIdAndTicketIdAndState(shoppingCarRequest.getUserId(), shoppingCarRequest.getTicketId(), (short) 4);

                order.setChildrenNumber(shoppingCarRequest.getNumber());
                order.setTotalMoney(order.getTotalMoney() + shoppingCarRequest.getNumber() * ticket.getChildrenTicketPrice());

                ticket.setChildrenNumber(ticket.getChildrenNumber() - shoppingCarRequest.getNumber());

                ticket.setUpdateTime(new Date());
                ticketDao.updateTicket(ticket);

                order.setUpdateTime(new Date());
                return orderDao.updateOrder(order);
            }else {
                return addOrderToCar(shoppingCarRequest,ticket);
            }
        } else {
             return addOrderToCar(shoppingCarRequest, ticket);
        }
    }


    @Override
    public Integer addOrderByLastMoney(AddShoppingCarRequest request) {
        User user = userDao.selectUserById(request.getUserId());
        Order order = new Order();
        Ticket ticket = ticketDao.selectTicketById(request.getTicketId());

        if(request.getSpec().equals("规格:学生")){
            order.setChildrenNumber(request.getNumber());
            order.setAdultNumber(0);
            order.setTotalMoney(ticket.getChildrenTicketPrice()*request.getNumber());

            ticket.setChildrenNumber(ticket.getChildrenNumber()-request.getNumber());
        }else {
            order.setAdultNumber(request.getNumber());
            order.setChildrenNumber(0);
            order.setTotalMoney(ticket.getAdultTicketPrice()*request.getNumber());

            ticket.setAdultNumber(ticket.getAdultNumber()-request.getNumber());
        }
        if(user.getLastMoney()>=order.getTotalMoney()){
            User admin = userDao.selectUserById(1L);
            admin.setLastMoney(admin.getLastMoney()+order.getTotalMoney());
            admin.setUpdateTime(new Date());
            userDao.updateUser(admin);

            user.setLastMoney(user.getLastMoney()-order.getTotalMoney());
            user.setUpdateTime(new Date());
            userDao.updateUser(user);

            ticket.setUpdateTime(new Date());
            ticketDao.updateTicket(ticket);

            order.setOrderState((short)1);
            order.setUserId(request.getUserId());
            order.setTicketId(request.getTicketId());
            order.setCreateTime(new Date());
            return orderDao.insertOrder(order);
        }else return -1;
    }

    @Override
    public Integer addOrderByOther(AddShoppingCarRequest request) {
        User user = userDao.selectUserById(request.getUserId());
        Order order = new Order();
        Ticket ticket = ticketDao.selectTicketById(request.getTicketId());

        if(request.getSpec().equals("规格:学生")){
            order.setChildrenNumber(request.getNumber());
            order.setAdultNumber(0);
            order.setTotalMoney(ticket.getChildrenTicketPrice()*request.getNumber());

            ticket.setChildrenNumber(ticket.getChildrenNumber()-request.getNumber());
        }else {
            order.setAdultNumber(request.getNumber());
            order.setChildrenNumber(0);
            order.setTotalMoney(ticket.getAdultTicketPrice()*request.getNumber());

            ticket.setAdultNumber(ticket.getAdultNumber()-request.getNumber());
        }
        User admin = userDao.selectUserById(1L);
        admin.setLastMoney(admin.getLastMoney()+order.getTotalMoney());
        admin.setUpdateTime(new Date());
        userDao.updateUser(admin);

        ticket.setUpdateTime(new Date());
        ticketDao.updateTicket(ticket);

        order.setOrderState((short)1);
        order.setUserId(request.getUserId());
        order.setTicketId(request.getTicketId());
        order.setCreateTime(new Date());
        return orderDao.insertOrder(order);
    }

    @Override
    public Integer payOrder(PayOrderRequest payOrderRequest) {
        User user = userDao.selectUserById(payOrderRequest.getUserId());

        List<Long> ids = payOrderRequest.getIds();
        List<Order> orders = new ArrayList<>();

        if(user.getLastMoney()>=payOrderRequest.getTotalMoney()){
            user.setLastMoney(user.getLastMoney()-payOrderRequest.getTotalMoney());
            user.setUpdateTime(new Date());
            userDao.updateUser(user);

            User admin = userDao.selectUserById(1L);
            admin.setLastMoney(admin.getLastMoney()+payOrderRequest.getTotalMoney());
            admin.setUpdateTime(new Date());
            userDao.updateUser(admin);

            for (Long id : ids) {
                Order order = orderDao.selectOrderById(id);
                order.setOrderState((short)1);
                order.setUpdateTime(new Date());
                orders.add(order);
            }
            return orderDao.payOrder(orders);
        }else {
            for (Long id : ids) {
                Order order = orderDao.selectOrderById(id);
                order.setOrderState((short)0);
                order.setUpdateTime(new Date());
                orders.add(order);
            }
            orderDao.payOrder(orders);
            return -1;
        }
    }

    @Override
    public Integer payOrderByOther(PayOrderByOtherRequest payOrderByOtherRequest) {
        List<Long> ids = payOrderByOtherRequest.getIds();
        List<Order> orders = new ArrayList<>();

        for (Long id : ids) {
            Order order = orderDao.selectOrderById(id);
            order.setOrderState((short)1);
            order.setUpdateTime(new Date());
            orders.add(order);
        }
        return orderDao.payOrder(orders);
    }

    @Override
    public Integer deleteOrder(IdsRequest idsRequest) {
        return orderDao.deleteOrder(idsRequest.getIds());
    }

    @Override
    public Integer chargeBackOrder(IdRequest idRequest) {
        Order order = orderDao.selectOrderById(idRequest.getId());
        User user = userDao.selectUserById(order.getUserId());
        Ticket ticket = ticketDao.selectTicketById(order.getTicketId());
        if(order!=null){
            if (order.getOrderState()==0) {
                return -2;//订单未付款
            } else if (order.getOrderState()==1){
                user.setLastMoney(user.getLastMoney()+order.getTotalMoney());
                user.setUpdateTime(new Date());
                userDao.updateUser(user);

                User admin = userDao.selectUserById(1L);
                admin.setLastMoney(admin.getLastMoney()-order.getTotalMoney());
                admin.setUpdateTime(new Date());
                userDao.updateUser(admin);

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
    public Integer updateShopCarNum(UpdateShopCarNumRequest request) {
        Order order = orderDao.selectOrderById(request.getId());
        Ticket ticket = ticketDao.selectTicketById(order.getTicketId());
        if(order.getAdultNumber()>0){
            order.setAdultNumber(request.getNumber());
            order.setTotalMoney(ticket.getAdultTicketPrice()*request.getNumber());
        }else {
            order.setChildrenNumber(request.getNumber());
            order.setTotalMoney(ticket.getChildrenTicketPrice()*request.getNumber());
        }
        order.setUpdateTime(new Date());
        return orderDao.updateOrder(order);
    }

    @Override
    public OrderResponse selectOrderById(IdRequest idRequest) {
        Order order = orderDao.selectOrderById(idRequest.getId());
        OrderResponse orderResponse = changeOrderResponse(order);
        return orderResponse;
    }

    @Override
    public AllOrderResponse selectAllOrder(PageNumRequest pageNumRequest) {
        PageHelper.startPage(1,pageNumRequest.getPageNum()*10);
        List<Order> orders = orderDao.selectAllOrder();
        PageInfo<Order> pageInfo = new PageInfo<>(orders);

        List<Order> orderList = pageInfo.getList();
        AllOrderResponse allOrderResponse = changeAllOrderResponse(orderList);
        allOrderResponse.setTotal(orderDao.countAllOrder());
        return allOrderResponse;
    }

    @Override
    public AllOrderResponse selectOrderByUserId(SelectOrderByUserIdRequest selectOrderByUserIdRequest) {
        PageHelper.startPage(selectOrderByUserIdRequest.getPageNum(),10);
        List<Order> orders = orderDao.selectOrderByUserId(selectOrderByUserIdRequest.getUserId());
        PageInfo<Order> pageInfo = new PageInfo<>(orders);

        List<Order> orderList = pageInfo.getList();
        AllOrderResponse allOrderResponse = changeAllOrderResponse(orderList);
        allOrderResponse.setTotal(orderDao.countOrderByUserId(selectOrderByUserIdRequest.getUserId()));
        return allOrderResponse;
    }

    @Override
    public AllOrderResponse selectOrderByTicketId(SelectOrderByTicketIdRequest selectOrderByTicketIdRequest) {
        PageHelper.startPage(selectOrderByTicketIdRequest.getPageNum(),10);
        List<Order> orders = orderDao.selectOrderByTicketId(selectOrderByTicketIdRequest.getTicketId());
        PageInfo<Order> pageInfo = new PageInfo<>(orders);

        List<Order> orderList = pageInfo.getList();
        AllOrderResponse allOrderResponse = changeAllOrderResponse(orderList);
        allOrderResponse.setTotal(orderDao.countOrderByTicketId(selectOrderByTicketIdRequest.getTicketId()));
        return allOrderResponse;
    }

    @Override
    public AllSelectOrderResponse selectShoppingCar(UserIdRequest userIdRequest) {
        List<Order> orders = orderDao.selectOrderByUserIdAndState(userIdRequest.getUserId(), (short) 4);
        if(!CollectionUtils.isEmpty(orders)) {
            AllSelectOrderResponse allSelectOrderResponse = changeAllSelectOrderResponse(orders);
            allSelectOrderResponse.setTotal(orderDao.countOrderByUserIdAndState(userIdRequest.getUserId(), (short) 4));
            return allSelectOrderResponse;
        }else return null;
    }

    @Override
    public AllSelectOrderResponse selectWaitPayment(UserIdRequest userIdRequest) {
        List<Order> orders = orderDao.selectOrderByUserIdAndState(userIdRequest.getUserId(), (short) 0);
        if(!CollectionUtils.isEmpty(orders)) {
            AllSelectOrderResponse allSelectOrderResponse = changeAllSelectOrderResponse(orders);
            allSelectOrderResponse.setTotal(orderDao.countOrderByUserIdAndState(userIdRequest.getUserId(), (short) 0));
            return allSelectOrderResponse;
        }else return null;
    }

    @Override
    public AllSelectOrderResponse selectChargeBack(UserIdRequest userIdRequest) {
        List<Order> orders = orderDao.selectOrderByUserIdAndState(userIdRequest.getUserId(), (short) 2);
        if(!CollectionUtils.isEmpty(orders)) {
            AllSelectOrderResponse allSelectOrderResponse = changeAllSelectOrderResponse(orders);
            allSelectOrderResponse.setTotal(orderDao.countOrderByUserIdAndState(userIdRequest.getUserId(), (short) 2));
            return allSelectOrderResponse;
        }else return null;
    }

    @Override
    public AllSelectOrderResponse selectSuccessOrder(UserIdRequest userIdRequest) {
        List<Order> orders = orderDao.selectOrderByUserIdAndState(userIdRequest.getUserId(), (short) 1);
        if(!CollectionUtils.isEmpty(orders)) {
            AllSelectOrderResponse allSelectOrderResponse = changeAllSelectOrderResponse(orders);
            allSelectOrderResponse.setTotal(orderDao.countOrderByUserIdAndState(userIdRequest.getUserId(), (short) 1));
            return allSelectOrderResponse;
        }else return null;
    }

    private OrderResponse changeOrderResponse(Order order){
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

    private SelectOrderResponse changeSelectOrderResponse(Order order){
        SelectOrderResponse shoppingCarResponse = new SelectOrderResponse();
        Ticket ticket = ticketDao.selectTicketById(order.getTicketId());
        BeanUtils.copyProperties(order,shoppingCarResponse);

        if(order.getAdultNumber().equals(0)){
            shoppingCarResponse.setSpec("规格:学生");
            shoppingCarResponse.setNumber(order.getChildrenNumber());
            shoppingCarResponse.setPrice(ticket.getChildrenTicketPrice());
        }else {
            shoppingCarResponse.setSpec("规格:成人");
            shoppingCarResponse.setNumber(order.getAdultNumber());
            shoppingCarResponse.setPrice(ticket.getAdultTicketPrice());
        }
        shoppingCarResponse.setChildrenTicketPrice(ticket.getChildrenTicketPrice());
        shoppingCarResponse.setAdultTicketPrice(ticket.getAdultTicketPrice());
        shoppingCarResponse.setTicketName(ticket.getTicketName());
        shoppingCarResponse.setTicketDescribe(ticket.getTicketDescribe());

        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(ticket.getScenicSpotId());
        shoppingCarResponse.setImg(scenicSpot.getImg());
        shoppingCarResponse.setScenicSpotId(scenicSpot.getId());

        return shoppingCarResponse;
    }

    private AllOrderResponse changeAllOrderResponse(List<Order> orderList) {
        AllOrderResponse allOrderResponse = new AllOrderResponse();
        List<OrderResponse> list = new ArrayList<>();
        for(Order order : orderList){
            OrderResponse orderResponse = changeOrderResponse(order);
            list.add(orderResponse);
        }
        allOrderResponse.setOrderResponseList(list);
        return allOrderResponse;
    }

    private AllSelectOrderResponse changeAllSelectOrderResponse(List<Order> orders){
        AllSelectOrderResponse allSelectOrderResponse = new AllSelectOrderResponse();
        List<SelectOrderResponse> list = new ArrayList<>();
        for(Order order : orders){
            SelectOrderResponse selectOrderResponse = changeSelectOrderResponse(order);
            list.add(selectOrderResponse);
        }
        allSelectOrderResponse.setSelectOrderResponseList(list);
        return allSelectOrderResponse;
    }

    private Integer addOrderToCar(AddShoppingCarRequest shoppingCarRequest,Ticket ticket){
        Order order = new Order();
        order.setUserId(shoppingCarRequest.getUserId());
        order.setTicketId(shoppingCarRequest.getTicketId());

        if(shoppingCarRequest.getSpec().equals("规格:学生")){
            order.setChildrenNumber(shoppingCarRequest.getNumber());
            order.setAdultNumber(0);
            order.setTotalMoney(ticket.getChildrenTicketPrice()*shoppingCarRequest.getNumber());

            ticket.setChildrenNumber(ticket.getChildrenNumber()-shoppingCarRequest.getNumber());
        }else {
            order.setAdultNumber(shoppingCarRequest.getNumber());
            order.setChildrenNumber(0);
            order.setTotalMoney(ticket.getAdultTicketPrice()*shoppingCarRequest.getNumber());

            ticket.setAdultNumber(ticket.getAdultNumber()-shoppingCarRequest.getNumber());

        }
        ticket.setUpdateTime(new Date());
        ticketDao.updateTicket(ticket);

        order.setOrderState((short)4);
        order.setCreateTime(new Date());
        return orderDao.insertOrder(order);
    }

}
