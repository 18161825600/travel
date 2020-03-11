package com.example.travel.service;

import com.example.travel.request.*;
import com.example.travel.response.*;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {

    Integer insertOrder(AddOrderRequest addOrderRequest);

    Integer insertShoppingCar(AddShoppingCarRequest shoppingCarRequest);

    Integer addPaymentOrder(AddPaymentOrderRequest request);

    Integer addOrderByLastMoney(AddShoppingCarRequest request);

    Integer addOrderByOther(AddShoppingCarRequest request);

    Integer payOrder(PayOrderRequest payOrderRequest);

    Integer payOrderByOther(PayOrderByOtherRequest payOrderByOtherRequest);

    Integer deleteOrder(IdsRequest idsRequest);

    Integer chargeBackOrder(IdRequest idRequest);

    Integer updateShopCarNum(UpdateShopCarNumRequest request);

    OrderResponse selectOrderById(IdRequest idRequest);

    AllOrderResponse selectAllOrder(PageNumRequest pageNumRequest);

    AllOrderResponse selectOrderByUserId(SelectOrderByUserIdRequest selectOrderByUserIdRequest);

    AllOrderResponse selectOrderByTicketId(SelectOrderByTicketIdRequest selectOrderByTicketIdRequest);

    AllSelectOrderResponse selectShoppingCar(UserIdRequest userIdRequest);

    AllSelectOrderResponse selectWaitPayment(UserIdRequest userIdRequest);

    AllSelectOrderResponse selectChargeBack(UserIdRequest userIdRequest);

    AllSelectOrderResponse selectSuccessOrder(UserIdRequest userIdRequest);
}
