package com.example.travel.service;

import com.example.travel.pojo.User;
import com.example.travel.request.*;
import com.example.travel.response.UserResponse;
import com.github.pagehelper.PageInfo;

public interface UserService {

    Integer registerUser(RegisterUserRequest registerUserRequest);

    User signIn(SignUserRequest signUserRequest);

    Integer deleteUser(Long id);

    Integer cashOutUserMoney(CashOutUserMoneyRequest cashOutUserMoneyRequest);

    Integer updateUserBankCard(UpdateUserBankCard updateUserBankCard);

    Integer updateUserBaseInfo(UpdateUserBaseInfoRequest updateUserBaseInfoRequest);

    Integer updateUserPassword(UpdateUserPasswordRequest updateUserPasswordRequest);

    Integer updateUserPhoneNum(UpdateUserPhoneNumRequest updateUserPhoneNumRequest);

    Integer insertUserPayPassword(UpdateUserPayPasswordRequest updateUserPayPasswordRequest);

    Integer updateUserPayPasswordByOld(UpdateUserPayPasswordRequest updateUserPayPasswordRequest);

    Integer investMoney(InvestMoney investMoney);

    Double selectUserLastMoney(Long id);

    PageInfo<UserResponse> selectAllUser(Integer pageNum);

    UserResponse selectUserByUserName(String userName);

    PageInfo<UserResponse> selectUserByNickName(String nickName,Integer pageNum);
}
