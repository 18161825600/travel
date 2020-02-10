package com.example.travel.controller;

import com.example.travel.common.TravelJsonResult;
import com.example.travel.request.AddOrderRequest;
import com.example.travel.response.OrderResponse;
import com.example.travel.response.SelectOrderResponse;
import com.example.travel.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "insert/order")
    public TravelJsonResult<String> insertOrder(@RequestBody AddOrderRequest addOrderRequest){
        Integer integer = orderService.insertOrder(addOrderRequest);
        if(integer==1){
            return TravelJsonResult.ok("添加成功");
        }else if(integer==2){
            return TravelJsonResult.ok("余额不足，等待付款");
        }else if(integer==-1) {
            return TravelJsonResult.errorMsg("门票不能为空");
        }else if(integer==-3){
            return TravelJsonResult.errorMsg("用户不存在");
        }else if(integer==-4){
            return TravelJsonResult.errorMsg("门票不存在");
        }else return TravelJsonResult.errorMsg("添加失败，请稍后重试");
    }

    @PostMapping(value = "shopping/car")
    public TravelJsonResult<String> insertShoppingCar(@RequestBody AddOrderRequest addOrderRequest){
        Integer integer = orderService.insertShoppingCar(addOrderRequest);
        if(integer==1){
            return TravelJsonResult.ok("添加到购物车成功");
        }else return TravelJsonResult.errorMsg("添加失败");
    }

    @DeleteMapping(value = "delete/order")
    public TravelJsonResult<String> deleteOrder(@RequestBody List<Long> ids){
        return TravelJsonResult.ok("删除了"+orderService.deleteOrder(ids)+"条记录");
    }

    @PutMapping(value = "chargeBack/order")
    public TravelJsonResult<String> chargeBackOrder(Long id){
        Integer integer = orderService.chargeBackOrder(id);
        if(integer==1){
            return TravelJsonResult.ok("退单成功");
        }else if(integer==-1){
            return TravelJsonResult.errorMsg("订单不存在");
        }else if(integer==-2){
            return TravelJsonResult.errorMsg("订单未付款，无法退款");
        }else if(integer==-3){
            return TravelJsonResult.errorMsg("订单已退款，请勿重复退款");
        }else if(integer==-4){
            return TravelJsonResult.errorMsg("门票过期，无法退款");
        }else return TravelJsonResult.errorMsg("退款失败请稍后重试");
    }

    @GetMapping(value = "select/order/by/id")
    public TravelJsonResult<OrderResponse> selectOrderById(Long id){
        return TravelJsonResult.ok(orderService.selectOrderById(id));
    }

    @GetMapping(value = "select/all/order")
    public TravelJsonResult<PageInfo<OrderResponse>> selectAllOrder(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(orderService.selectAllOrder(pageNum));
    }

    @GetMapping(value = "select/order/by/userId")
    public TravelJsonResult<PageInfo<OrderResponse>> selectOrderByUserId(Long userId,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(orderService.selectOrderByUserId(userId, pageNum));
    }

    @GetMapping(value = "select/order/by/ticketId")
    public TravelJsonResult<PageInfo<OrderResponse>> selectOrderByTicketId(Long ticketId,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(orderService.selectOrderByTicketId(ticketId, pageNum));
    }

    @GetMapping(value = "select/shopping/car")
    public TravelJsonResult<List<SelectOrderResponse>> selectShoppingCar(Long userId){
        return TravelJsonResult.ok(orderService.selectShoppingCar(userId));
    }

    @GetMapping(value = "select/wait/payment")
    public TravelJsonResult<List<SelectOrderResponse>> selectWaitPayment(Long userId){
        return TravelJsonResult.ok(orderService.selectWaitPayment(userId));
    }

    @GetMapping(value = "select/charge/back")
    public TravelJsonResult<List<SelectOrderResponse>> selectChargeBack(Long userId){
        return TravelJsonResult.ok(orderService.selectChargeBack(userId));
    }

    @GetMapping(value = "select/success/order")
    public TravelJsonResult<List<SelectOrderResponse>> selectSuccessOrder(Long userId){
        return TravelJsonResult.ok(orderService.selectSuccessOrder(userId));
    }
}
