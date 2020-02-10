package com.example.travel.service.impl;

import com.example.travel.dao.UserDao;
import com.example.travel.pojo.User;
import com.example.travel.request.*;
import com.example.travel.response.SignUserResponse;
import com.example.travel.response.UserResponse;
import com.example.travel.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer registerUser(RegisterUserRequest registerUserRequest) {
        User user = new User();
        BeanUtils.copyProperties(registerUserRequest, user);
        user.setLastMoney(0.0);
        user.setCreateTime(new Date());
        return userDao.registerUser(user);
    }

    @Override
    public User signIn(SignUserRequest signUserRequest) {
        return userDao.signIn(signUserRequest.getUserName(), signUserRequest.getPassword());
    }

    @Override
    public Integer deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    @Override
    public Integer cashOutUserMoney(CashOutUserMoneyRequest cashOutUserMoneyRequest) {
        User user = userDao.selectUserById(cashOutUserMoneyRequest.getId());
        if(user.getLastMoney()>=cashOutUserMoneyRequest.getMoney()){
            user.setLastMoney(user.getLastMoney()-cashOutUserMoneyRequest.getMoney());
            user.setUpdateTime(new Date());
            return userDao.updateUser(user);
        }else return -2;//余额不足
    }

    @Override
    public Integer updateUserBankCard(UpdateUserBankCard updateUserBankCard) {
        User user = userDao.selectUserById(updateUserBankCard.getId());
        user.setBankCard(updateUserBankCard.getBankCard());
        user.setUpdateTime(new Date());
        return userDao.updateUser(user);
    }

    @Override
    public Integer updateUserBaseInfo(UpdateUserBaseInfoRequest updateUserBaseInfoRequest) {
        User user = new User();
        BeanUtils.copyProperties(updateUserBaseInfoRequest,user);
        user.setUpdateTime(new Date());
        return userDao.updateUser(user);
    }

    @Override
    public Integer updateUserPassword(UpdateUserPasswordRequest updateUserPasswordRequest) {
        User user = userDao.selectUserByUserName(updateUserPasswordRequest.getUserName());
        if(updateUserPasswordRequest.getPassword().equals(user.getPassword())){
            return -1;//新密码不能和旧密码相同
        }else {
            user.setPassword(updateUserPasswordRequest.getPassword());
            user.setUpdateTime(new Date());
            return userDao.updateUser(user);
        }
    }

    @Override
    public Integer updateUserPhoneNum(UpdateUserPhoneNumRequest updateUserPhoneNumRequest) {
        User user = userDao.selectUserById(updateUserPhoneNumRequest.getId());
        if (updateUserPhoneNumRequest.getPhoneNumber().equals(user.getPhoneNumber())) {
            return -1;//新手机号不能和旧手机号一样
        } else {
            user.setPhoneNumber(updateUserPhoneNumRequest.getPhoneNumber());
            user.setUpdateTime(new Date());
            return userDao.updateUser(user);
        }
    }

    @Override
    public Integer insertUserPayPassword(UpdateUserPayPasswordRequest updateUserPayPasswordRequest) {
        User user = userDao.selectUserById(updateUserPayPasswordRequest.getId());
        user.setPaymentPassword(updateUserPayPasswordRequest.getPaymentPassword());
        user.setUpdateTime(new Date());
        return userDao.updateUser(user);
    }

    @Override
    public Integer updateUserPayPasswordByOld(UpdateUserPayPasswordRequest updateUserPayPasswordRequest) {
        User user = userDao.selectUserById(updateUserPayPasswordRequest.getId());
        user.setPaymentPassword(updateUserPayPasswordRequest.getPaymentPassword());
        return userDao.updateUser(user);
    }

    @Override
    public Integer investMoney(InvestMoney investMoney) {
        User user = userDao.selectUserById(investMoney.getId());
        user.setLastMoney(user.getLastMoney() + investMoney.getMoney());
        user.setUpdateTime(new Date());
        return userDao.updateUser(user);
    }

    @Override
    public Double selectUserLastMoney(Long id) {
        User user = userDao.selectUserById(id);
        return user.getLastMoney();
    }

    @Override
    public PageInfo<UserResponse> selectAllUser(Integer pageNum) {
        List<User> users = userDao.selectAllUser();
        PageHelper.startPage(pageNum,10);
        List<UserResponse> list = new ArrayList<>();
        for(User user : users){
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user,userResponse);
            userResponse.setTotal(userDao.countAllUser());
            list.add(userResponse);
        }
        PageInfo<UserResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public UserResponse selectUserByUserName(String userName) {
        User user = userDao.selectUserByUserName(userName);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user,userResponse);
        userResponse.setTotal(1);
        return userResponse;
    }

    @Override
    public PageInfo<UserResponse> selectUserByNickName(String nickName,Integer pageNum) {
        List<User> users = userDao.selectUserByNickName(nickName);
        PageHelper.startPage(pageNum,10);
        List<UserResponse> list = new ArrayList<>();
        for(User user : users){
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user,userResponse);
            userResponse.setTotal(userDao.countByNickName(nickName));
            list.add(userResponse);
        }
        PageInfo<UserResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
