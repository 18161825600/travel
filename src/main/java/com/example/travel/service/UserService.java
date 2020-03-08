package com.example.travel.service;

import com.example.travel.request.*;
import com.example.travel.response.SignUserResponse;
import com.example.travel.response.UserResponse;
import com.github.pagehelper.PageInfo;

public interface UserService {

    Integer registerUser(RegisterUserRequest registerUserRequest);

    <T>T signIn(SignUserRequest signUserRequest);

    Long signInAdmin(SignUserRequest signUserRequest);

    Integer deleteUser(IdRequest idRequest);

    Integer cashOutUserMoney(CashOutUserMoneyRequest cashOutUserMoneyRequest);

    Integer updateUserBankCard(UpdateUserBankCard updateUserBankCard);

    Integer updateUserBaseInfo(UpdateUserBaseInfoRequest updateUserBaseInfoRequest);

    Integer updateUserNickName(UpdateUserNickName updateUserNickName);

    Integer updateUserImgUrl(UpdateUserImgUrl updateUserImgUrl);

    Integer updateUserPassword(UpdateUserPasswordRequest updateUserPasswordRequest);

    Integer updateUserPhoneNum(UpdateUserPhoneNumRequest updateUserPhoneNumRequest);

    Integer insertUserPayPassword(UpdateUserPayPasswordRequest updateUserPayPasswordRequest);

    Integer updateUserPayPasswordByOld(UpdateUserPayPasswordRequest updateUserPayPasswordRequest);

    Integer investMoney(InvestMoney investMoney);

    Double selectUserLastMoney(IdRequest idRequest);

    SignUserResponse selectUserById(IdRequest idRequest);

    PageInfo<UserResponse> selectAllUser(PageNumRequest pageNumRequest);

    UserResponse selectUserByUserName(String userName);

    PageInfo<UserResponse> selectUserByNickName(String nickName,Integer pageNum);
}
