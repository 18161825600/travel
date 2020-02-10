package com.example.travel.controller;

import com.example.travel.common.TravelJsonResult;
import com.example.travel.pojo.User;
import com.example.travel.request.*;
import com.example.travel.response.UserResponse;
import com.example.travel.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "register/user")
    public TravelJsonResult<String> registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        try {
            Integer integer = userService.registerUser(registerUserRequest);
            if(integer==1){
                return TravelJsonResult.ok("用户注册成功");
            }
        }catch (Exception e){
            return TravelJsonResult.errorException("用户名已存在");
        }return TravelJsonResult.errorMsg("服务器错误");
    }

    @GetMapping(value = "singIn/user")
    public TravelJsonResult<User> signIn(@RequestBody SignUserRequest signUserRequest){
        User user = userService.signIn(signUserRequest);
        if(user!=null){
            return TravelJsonResult.ok(user);
        }else return TravelJsonResult.errorMsg("用户名或密码错误");
    }

    @DeleteMapping(value = "delete/user")
    public TravelJsonResult<String> deleteUser(Long id){
        Integer integer = userService.deleteUser(id);
        if(integer==1){
            return TravelJsonResult.ok("删除成功");
        }else return TravelJsonResult.errorMsg("删除失败");
    }

    @PutMapping(value = "cashOut/user/money")
    public TravelJsonResult<String> cashOutUserMoney(@RequestBody CashOutUserMoneyRequest cashOutUserMoneyRequest){
        Integer integer = userService.cashOutUserMoney(cashOutUserMoneyRequest);
        if(integer==1){
            return TravelJsonResult.ok("提现成功");
        }else if(integer==-2){
            return TravelJsonResult.errorMsg("用户余额不足");
        }else return TravelJsonResult.errorMsg("稍后重试");
    }

    @PutMapping(value = "update/user/bank/card")
    public TravelJsonResult<String> updateUserBankCard(@RequestBody UpdateUserBankCard updateUserBankCard){
        Integer integer = userService.updateUserBankCard(updateUserBankCard);
        if(integer==1){
            return TravelJsonResult.ok("修改银行卡成功");
        }else return TravelJsonResult.errorMsg("修改失败，稍后再试！");
    }

    @PutMapping(value = "update/user/base/info")
    public TravelJsonResult<String> updateUserBaseInfo(@RequestBody UpdateUserBaseInfoRequest updateUserBaseInfoRequest){
        Integer integer = userService.updateUserBaseInfo(updateUserBaseInfoRequest);
        if(integer==1){
            return TravelJsonResult.ok("修改成功");
        }
        else return TravelJsonResult.errorMsg("修改失败");
    }

    @PutMapping(value = "update/user/password")
    public TravelJsonResult<String> updateUserPassword(@RequestBody UpdateUserPasswordRequest updateUserPasswordRequest){
        Integer integer = userService.updateUserPassword(updateUserPasswordRequest);
        if(integer==1){
            return TravelJsonResult.ok("修改成功");
        }else if(integer==-1){
            return TravelJsonResult.errorMsg("新密码不能和旧密码相同");
        }else return TravelJsonResult.errorMsg("修改失败");
    }

    @PutMapping(value = "update/user/phonenum")
    public TravelJsonResult<String> updateUserPhoneNum(@RequestBody UpdateUserPhoneNumRequest updateUserPhoneNumRequest){
        Integer integer = userService.updateUserPhoneNum(updateUserPhoneNumRequest);
        if(integer==1){
            return TravelJsonResult.ok("修改成功");
        }else if(integer==-1){
            return TravelJsonResult.errorMsg("新手机号不能和旧手机号一样");
        }  else return TravelJsonResult.errorMsg("修改失败");
    }

    //第一次添加支付密码
    @PutMapping(value = "insert/user/paypassword")
    public TravelJsonResult<String> insertUserPayPassword(@RequestBody UpdateUserPayPasswordRequest updateUserPayPasswordRequest){
        Integer integer = userService.insertUserPayPassword(updateUserPayPasswordRequest);
        if(integer==1){
            return TravelJsonResult.ok("修改成功");
        }else return TravelJsonResult.errorMsg("修改失败");
    }

    //通过支付密码修改支付密码
    @PutMapping(value = "update/user/paypassword/by/old")
    public TravelJsonResult<String> updateUserPayPasswordByOld(@RequestBody UpdateUserPayPasswordRequest updateUserPayPasswordRequest){
        Integer integer = userService.updateUserPayPasswordByOld(updateUserPayPasswordRequest);
        if(integer==1){
            return TravelJsonResult.ok("修改支付密码成功");
        }else return TravelJsonResult.errorMsg("修改支付密码失败");
    }

    @PutMapping(value = "invest/money")
    public TravelJsonResult<String> investMoney(@RequestBody InvestMoney investMoney){
        Integer integer = userService.investMoney(investMoney);
        if(integer==1){
            return TravelJsonResult.ok("充值成功");
        }else return TravelJsonResult.errorMsg("充值失败");
    }

    @GetMapping(value = "select/user/lastmoney")
    public TravelJsonResult<Double> selectUserLastMoney(Long id){
        Double lastMoney = userService.selectUserLastMoney(id);
        return TravelJsonResult.ok(lastMoney);
    }

    @GetMapping(value = "select/all/user")
    public TravelJsonResult<PageInfo<UserResponse>> selectAllUser(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(userService.selectAllUser(pageNum));
    }

    @GetMapping(value = "select/user/by/username")
    public TravelJsonResult<UserResponse> selectUserByUserName(String userName){
        return TravelJsonResult.ok(userService.selectUserByUserName(userName));
    }

    @GetMapping(value = "select/user/by/nickname")
    public TravelJsonResult<PageInfo<UserResponse>> selectUserByNickName(String nickName,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(userService.selectUserByNickName(nickName, pageNum));
    }
}
