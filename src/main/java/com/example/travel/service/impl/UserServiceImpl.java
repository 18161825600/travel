package com.example.travel.service.impl;

import com.example.travel.dao.*;
import com.example.travel.pojo.Order;
import com.example.travel.pojo.Ticket;
import com.example.travel.pojo.User;
import com.example.travel.request.*;
import com.example.travel.response.ShoppingCarResponse;
import com.example.travel.response.SignUserResponse;
import com.example.travel.response.UserResponse;
import com.example.travel.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private FavoriteDao favoriteDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private TicketDao ticketDao;

    @Override
    public Integer registerUser(RegisterUserRequest registerUserRequest) {
        User user = new User();
        BeanUtils.copyProperties(registerUserRequest, user);
        user.setLastMoney(0.0);
        user.setUserPrivilege((short)0);
        user.setCreateTime(new Date());
        return userDao.registerUser(user);
    }

    @Override
    public <T>T signIn(SignUserRequest signUserRequest) {
        User user = userDao.signIn(signUserRequest.getUserName(), signUserRequest.getPassword());

        if(user!=null) {
            SignUserResponse signUserResponse = changeUserResponse(user);
            return (T)signUserResponse;
        }else return null;
    }

    @Override
    public Long signInAdmin(SignUserRequest signUserRequest) {
        User user = userDao.signIn(signUserRequest.getUserName(), signUserRequest.getPassword());
        if(user!=null){
            if(user.getUserPrivilege()==(short)1){
                return user.getId();
            }return -2L;//权限不足
        }return -1L;//用户不存在
    }

    @Override
    public Integer deleteUser(IdRequest idRequest) {
        return userDao.deleteUser(idRequest.getId());
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
    public Integer updateUserNickName(UpdateUserNickName updateUserNickName) {
        User user = userDao.selectUserById(updateUserNickName.getId());
        user.setNickName(updateUserNickName.getNickName());
        user.setUpdateTime(new Date());
        return userDao.updateUser(user);
    }

    @Override
    public Integer updateUserImgUrl(UpdateUserImgUrl updateUserImgUrl) {
        User user = userDao.selectUserById(updateUserImgUrl.getId());
        user.setImgUrl(updateUserImgUrl.getImgUrl());
        user.setUpdateTime(new Date());
        return userDao.updateUser(user);
    }

    @Override
    public Integer updateUserPassword(UpdateUserPasswordRequest updateUserPasswordRequest) {
        User user = userDao.selectUserById(updateUserPasswordRequest.getId());
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
    public Double investMoney(InvestMoney investMoney) {
        User user = userDao.selectUserById(investMoney.getUserId());
        user.setLastMoney(user.getLastMoney() + investMoney.getMoney());
        user.setUpdateTime(new Date());
        userDao.updateUser(user);
        return user.getLastMoney();
    }

    @Override
    public Double selectUserLastMoney(IdRequest idRequest) {
        User user = userDao.selectUserById(idRequest.getId());
        return user.getLastMoney();
    }

    @Override
    public SignUserResponse selectUserById(IdRequest idRequest) {
        User user = userDao.selectUserById(idRequest.getId());
        SignUserResponse signUserResponse = changeUserResponse(user);
        return signUserResponse;
    }

    @Override
    public PageInfo<UserResponse> selectAllUser(PageNumRequest pageNumRequest) {
        PageHelper.startPage(1,pageNumRequest.getPageNum()*10);
        List<User> users = userDao.selectAllUser();
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
        PageHelper.startPage(1,pageNum*10);
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

    public SignUserResponse changeUserResponse(User user) {
        SignUserResponse signUserResponse = new SignUserResponse();
        BeanUtils.copyProperties(user,signUserResponse);

        signUserResponse.setFavoriteTotal(favoriteDao.countFavoriteByUserId(user.getId()));
        signUserResponse.setCommentTotal(commentDao.countByUserId(user.getId()));

        List<Order> orderList = orderDao.selectOrderByUserIdAndState(user.getId(), (short) 4);
        List<ShoppingCarResponse> list = new ArrayList<>();
        for (Order order : orderList) {
            ShoppingCarResponse shoppingCarResponse = new ShoppingCarResponse();
            BeanUtils.copyProperties(order, shoppingCarResponse);

            shoppingCarResponse.setOrderId(order.getId());

            Ticket ticket = ticketDao.selectTicketById(order.getTicketId());
            shoppingCarResponse.setTicketName(ticket.getTicketName());
            shoppingCarResponse.setAdultTicketPrice(ticket.getAdultTicketPrice());
            shoppingCarResponse.setChildrenTicketPrice(ticket.getChildrenTicketPrice());

            list.add(shoppingCarResponse);
        }
        signUserResponse.setShoppingCarResponseList(list);
        signUserResponse.setShoppingCarTotal(orderDao.countOrderByUserIdAndState(user.getId(), (short) 4));
        return signUserResponse;
    }

}
