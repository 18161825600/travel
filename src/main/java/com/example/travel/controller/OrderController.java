package com.example.travel.controller;

import com.example.travel.common.TravelJsonResult;
import com.example.travel.request.*;
import com.example.travel.response.*;
import com.example.travel.service.OrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PastOrPresent;
import java.util.List;

@RestController
@CrossOrigin
@Api
public class OrderController {

    @Autowired
    private OrderService orderService;

//    @PostMapping(value = "insert/order")
//    @ApiOperation(value = "之前写的直接下订单")
//    public TravelJsonResult<String> insertOrder(@RequestBody AddOrderRequest addOrderRequest){
//        Integer integer = orderService.insertOrder(addOrderRequest);
//        if(integer==1){
//            return TravelJsonResult.ok();
//        }else if(integer==2){
//            return TravelJsonResult.errorMsg("false");
//        }else if(integer==-1) {
//            return TravelJsonResult.errorMsg("门票不能为空");
//        }else if(integer==-3){
//            return TravelJsonResult.errorMsg("用户不存在");
//        }else if(integer==-4){
//            return TravelJsonResult.errorMsg("门票不存在");
//        }else return TravelJsonResult.errorMsg("添加失败，请稍后重试");
//    }

    @ApiOperation(value = "添加购物车")
    @PostMapping(value = "shopping/car")
    public TravelJsonResult<String> insertShoppingCar(@RequestBody AddShoppingCarRequest shoppingCarRequest){
        Integer integer = orderService.insertShoppingCar(shoppingCarRequest);
        if(!StringUtils.isEmpty(integer)){
            return TravelJsonResult.ok(integer);
        }else return TravelJsonResult.errorMsg("false");
    }

    @ApiOperation(value = "通过余额直接支付")
    @PostMapping(value = "add/order/by/lastMoney")
    public TravelJsonResult<String> addOrderByLastMoney(@RequestBody AddShoppingCarRequest request){
        Integer integer = orderService.addOrderByLastMoney(request);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @ApiOperation(value = "通过其他方式直接支付")
    @PostMapping(value = "add/order/by/other")
    public TravelJsonResult<String> addOrderByOther(@RequestBody AddShoppingCarRequest request){
        Integer integer = orderService.addOrderByOther(request);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @ApiOperation(value = "用余额支付")
    @PutMapping(value = "pay/orders")
    public TravelJsonResult<String> payOrder(@RequestBody PayOrderRequest payOrderRequest){
        Integer integer = orderService.payOrder(payOrderRequest);
        if(integer==-1){
            return TravelJsonResult.errorMsg("false");
        }else return TravelJsonResult.ok();
    }

    @ApiOperation(value = "通过别的方式支付")
    @PutMapping(value = "pay/order/by/other")
    public TravelJsonResult<String> payOrderByOther(@RequestBody PayOrderByOtherRequest payOrderByOtherRequest){
        Integer integer = orderService.payOrderByOther(payOrderByOtherRequest);
        if(integer==0){
            return TravelJsonResult.errorMsg("false");
        }else return TravelJsonResult.ok();
    }

    @ApiOperation(value = "批量删除订单")
    @DeleteMapping(value = "delete/order")
    public TravelJsonResult<String> deleteOrder(@RequestBody IdsRequest idsRequest){
        return TravelJsonResult.ok("删除了"+orderService.deleteOrder(idsRequest)+"条记录");
    }

//    @ApiOperation(value = "昨天写的第二次修改购物车里面的信息")
//    @PutMapping(value = "update/shopping/car")
//    public TravelJsonResult<String> updateShoppingCar(@RequestBody AddShoppingCarRequest shoppingCarRequest){
//        Integer integer = orderService.updateShoppingCar(shoppingCarRequest);
//        if(integer==1){
//            return TravelJsonResult.ok();
//        }else return TravelJsonResult.errorMsg("false");
//    }

    @ApiOperation(value = "退单")
    @PutMapping(value = "chargeBack/order")
    public TravelJsonResult<String> chargeBackOrder(@RequestBody IdRequest idRequest){
        Integer integer = orderService.chargeBackOrder(idRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else if(integer==-1){
            return TravelJsonResult.errorMsg("false");
        }else if(integer==-2){
            return TravelJsonResult.errorMsg("订单未付款，无法退款");
        }else if(integer==-3){
            return TravelJsonResult.errorMsg("订单已退款，请勿重复退款");
        }else if(integer==-4){
            return TravelJsonResult.errorMsg("门票过期，无法退款");
        }else return TravelJsonResult.errorMsg("退款失败请稍后重试");
    }

    @ApiOperation(value = "通过订单id查找订单")
    @PostMapping(value = "select/order/by/id")
    public TravelJsonResult<OrderResponse> selectOrderById(@RequestBody IdRequest idRequest){
        return TravelJsonResult.ok(orderService.selectOrderById(idRequest));
    }

    @ApiOperation(value = "查数据库中所以的订单（管理员）")
    @PostMapping(value = "select/all/order")
    public TravelJsonResult<AllOrderResponse> selectAllOrder(@RequestBody PageNumRequest pageNumRequest){
        return TravelJsonResult.ok(orderService.selectAllOrder(pageNumRequest));
    }

    @ApiOperation(value = "通过用户id查找订单")
    @PostMapping(value = "select/order/by/userId")
    public TravelJsonResult<AllOrderResponse> selectOrderByUserId(@RequestBody SelectOrderByUserIdRequest selectOrderByUserIdRequest){
        return TravelJsonResult.ok(orderService.selectOrderByUserId(selectOrderByUserIdRequest));
    }

    @ApiOperation(value = "通过门票id查找订单")
    @PostMapping(value = "select/order/by/ticketId")
    public TravelJsonResult<AllOrderResponse> selectOrderByTicketId(@RequestBody SelectOrderByTicketIdRequest selectOrderByTicketIdRequest){
        return TravelJsonResult.ok(orderService.selectOrderByTicketId(selectOrderByTicketIdRequest));
    }

    @ApiOperation(value = "查找用户购物车")
    @PostMapping(value = "select/shopping/car")
    public TravelJsonResult<AllSelectOrderResponse> selectShoppingCar(@RequestBody UserIdRequest userIdRequest){
        AllSelectOrderResponse response = orderService.selectShoppingCar(userIdRequest);
        if(response==null){
            return TravelJsonResult.errorMsg("false");
        }else return TravelJsonResult.ok(response);
    }

    @ApiOperation(value = "查找用户等待支付的订单")
    @PostMapping(value = "select/wait/payment")
    public TravelJsonResult<AllSelectOrderResponse> selectWaitPayment(@RequestBody UserIdRequest userIdRequest){
        AllSelectOrderResponse response = orderService.selectWaitPayment(userIdRequest);
        if(response==null){
            return TravelJsonResult.errorMsg("false");
        }else return TravelJsonResult.ok(response);
    }

    @ApiOperation(value = "查找用户已退款的订单")
    @PostMapping(value = "select/charge/back")
    public TravelJsonResult<AllSelectOrderResponse> selectChargeBack(@RequestBody UserIdRequest userIdRequest){
        AllSelectOrderResponse response = orderService.selectChargeBack(userIdRequest);
        if(response==null){
            return TravelJsonResult.errorMsg("false");
        }else return TravelJsonResult.ok(response);
    }

    @ApiOperation(value = "查找用户支付成功的订单")
    @PostMapping(value = "select/success/order")
    public TravelJsonResult<AllSelectOrderResponse> selectSuccessOrder(@RequestBody UserIdRequest userIdRequest){
        AllSelectOrderResponse response = orderService.selectSuccessOrder(userIdRequest);
        if(response==null){
            return TravelJsonResult.errorMsg("false");
        }else return TravelJsonResult.ok(response);
    }

    @PostMapping("update/shopCar/number")
    public TravelJsonResult<Integer> updateShopCarNum(@RequestBody UpdateShopCarNumRequest request){
        return TravelJsonResult.ok(orderService.updateShopCarNum(request));
    }
}
