package com.example.travel.service;

import com.example.travel.request.*;
import com.example.travel.response.*;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {

    Integer insertOrder(AddOrderRequest addOrderRequest);

    Integer insertShoppingCar(AddShoppingCarRequest shoppingCarRequest);

    AddPaymentOrderResponse addPaymentOrder(AddPaymentOrderRequest request);

    Integer addOrderByLastMoney(AddShoppingCarRequest request);

    Integer addOrderByOther(AddShoppingCarRequest request);

    CallWhatResponse callWhat(CallWhatRequest request);

    OrderIdsResponse changeOrderStateByIds(OrderIdsRequest request);

    Integer closePayOrder(ClosePayOrderRequest request);

    Integer payOrder(PayOrderRequest payOrderRequest);

    PayOrderByOtherResponse payOrderByOther(PayOrderByOtherRequest payOrderByOtherRequest);

    Integer deleteOrder(IdsRequest idsRequest);

    Integer chargeBackOrder(IdRequest idRequest);

    Integer updateShopCarNum(UpdateShopCarNumRequest request);

    OrderResponse selectOrderById(IdRequest idRequest);

    AllOrderResponse selectAllOrder(PageNumRequest pageNumRequest);

    AllOrderResponse selectOrderByUserId(SelectOrderByUserIdRequest selectOrderByUserIdRequest);

    AllOrderResponse selectOrderByTicketId(SelectOrderByTicketIdRequest selectOrderByTicketIdRequest);

    AllOrderResponse selectShoppingCar(UserIdRequest userIdRequest);

    AllOrderResponse selectWaitPayment(UserIdRequest userIdRequest);

    AllOrderResponse selectChargeBack(UserIdRequest userIdRequest);

    AllOrderResponse selectSuccessOrder(UserIdRequest userIdRequest);
}
