package com.example.travel.controller;

import com.example.travel.common.TravelJsonResult;
import com.example.travel.request.*;
import com.example.travel.response.SignUserResponse;
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
                return TravelJsonResult.ok();
            }
        }catch (Exception e){
            return TravelJsonResult.errorMsg("false");
        }return TravelJsonResult.errorMsg("-1");
    }

    @PostMapping(value = "signIn/user")
    public TravelJsonResult<SignUserResponse> signIn(@RequestBody SignUserRequest signUserRequest){
        SignUserResponse signUserResponse = userService.signIn(signUserRequest);
        if(signUserResponse!=null){
            return TravelJsonResult.ok(signUserResponse);
        }else return TravelJsonResult.errorMsg("false");
    }

    @PostMapping(value = "signIn/admin")
    public TravelJsonResult<Long> signInAdmin(@RequestBody SignUserRequest signUserRequest){
        Long id = userService.signInAdmin(signUserRequest);
        if(id==-1L){
            return TravelJsonResult.ok("用户不存在");
        }else if(id==-2L){
            return TravelJsonResult.ok("用户权限不足");
        }else return TravelJsonResult.ok(id);
    }

    @DeleteMapping(value = "delete/user")
    public TravelJsonResult<String> deleteUser(@RequestBody IdRequest idRequest){
        Integer integer = userService.deleteUser(idRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @PutMapping(value = "cashOut/user/money")
    public TravelJsonResult<String> cashOutUserMoney(@RequestBody CashOutUserMoneyRequest cashOutUserMoneyRequest){
        Integer integer = userService.cashOutUserMoney(cashOutUserMoneyRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else if(integer==-2){
            return TravelJsonResult.errorMsg("false");
        }else return TravelJsonResult.ok("稍后重试");
    }

    @PutMapping(value = "update/user/bank/card")
    public TravelJsonResult<String> updateUserBankCard(@RequestBody UpdateUserBankCard updateUserBankCard){
        Integer integer = userService.updateUserBankCard(updateUserBankCard);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

//    @PutMapping(value = "update/user/base/info")
//    public TravelJsonResult<String> updateUserBaseInfo(@RequestBody UpdateUserBaseInfoRequest updateUserBaseInfoRequest){
//        Integer integer = userService.updateUserBaseInfo(updateUserBaseInfoRequest);
//        if(integer==1){
//            return TravelJsonResult.ok("修改成功");
//        }
//        else return TravelJsonResult.errorMsg("修改失败");
//    }

    @PutMapping(value = "update/user/nickName")
    public TravelJsonResult<String> updateUserNickName(@RequestBody UpdateUserNickName updateUserNickName){
        Integer integer = userService.updateUserNickName(updateUserNickName);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @PutMapping(value = "update/user/imgUrl")
    public TravelJsonResult<String> updateUserImgUrl(@RequestBody  UpdateUserImgUrl updateUserImgUrl){
        Integer integer = userService.updateUserImgUrl(updateUserImgUrl);
        if (integer == 1) {
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @PutMapping(value = "update/user/password")
    public TravelJsonResult<String> updateUserPassword(@RequestBody UpdateUserPasswordRequest updateUserPasswordRequest){
        Integer integer = userService.updateUserPassword(updateUserPasswordRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else if(integer==-1){
            return TravelJsonResult.errorMsg("false");
        }else return TravelJsonResult.ok("修改失败");
    }

    @PutMapping(value = "update/user/phonenum")
    public TravelJsonResult<String> updateUserPhoneNum(@RequestBody UpdateUserPhoneNumRequest updateUserPhoneNumRequest){
        Integer integer = userService.updateUserPhoneNum(updateUserPhoneNumRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else if(integer==-1){
            return TravelJsonResult.errorMsg("false");
        }  else return TravelJsonResult.ok("修改失败");
    }

    //第一次添加支付密码
    @PutMapping(value = "insert/user/paypassword")
    public TravelJsonResult<String> insertUserPayPassword(@RequestBody UpdateUserPayPasswordRequest updateUserPayPasswordRequest){
        Integer integer = userService.insertUserPayPassword(updateUserPayPasswordRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    //通过支付密码修改支付密码
    @PutMapping(value = "update/user/paypassword/by/old")
    public TravelJsonResult<String> updateUserPayPasswordByOld(@RequestBody UpdateUserPayPasswordRequest updateUserPayPasswordRequest){
        Integer integer = userService.updateUserPayPasswordByOld(updateUserPayPasswordRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @PutMapping(value = "invest/money")
    public TravelJsonResult<String> investMoney(@RequestBody InvestMoney investMoney){
        Integer integer = userService.investMoney(investMoney);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @PostMapping(value = "select/user/lastmoney")
    public TravelJsonResult<Double> selectUserLastMoney(@RequestBody IdRequest idRequest){
        Double lastMoney = userService.selectUserLastMoney(idRequest);
        return TravelJsonResult.ok(lastMoney);
    }

    @PostMapping(value = "select/user/by/id")
    public TravelJsonResult<SignUserResponse> selectUserById(@RequestBody IdRequest idRequest){
        return TravelJsonResult.ok(userService.selectUserById(idRequest));
    }

    @PostMapping(value = "select/all/user")
    public TravelJsonResult<PageInfo<UserResponse>> selectAllUser(@RequestBody PageNumRequest pageNumRequest){
        return TravelJsonResult.ok(userService.selectAllUser(pageNumRequest));
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
