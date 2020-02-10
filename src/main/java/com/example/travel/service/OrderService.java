package com.example.travel.service;

import com.example.travel.request.AddOrderRequest;
import com.example.travel.response.OrderResponse;
import com.example.travel.response.SelectOrderResponse;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {

    Integer insertOrder(AddOrderRequest addOrderRequest);

    Integer insertShoppingCar(AddOrderRequest addOrderRequest);

    Integer deleteOrder(List<Long> ids);

    Integer chargeBackOrder(Long id);

    OrderResponse selectOrderById(Long id);

    PageInfo<OrderResponse> selectAllOrder(Integer pageNum);

    PageInfo<OrderResponse> selectOrderByUserId(Long userId,Integer pageNum);

    PageInfo<OrderResponse> selectOrderByTicketId(Long ticketId,Integer pageNum);

    List<SelectOrderResponse> selectShoppingCar(Long userId);

    List<SelectOrderResponse> selectWaitPayment(Long userId);

    List<SelectOrderResponse> selectChargeBack(Long userId);

    List<SelectOrderResponse> selectSuccessOrder(Long userId);
}
