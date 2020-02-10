package com.example.travel.dao;

import com.example.travel.mapper.UserMapper;
import com.example.travel.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public User signIn(String userName, String password){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName",userName)
                .andEqualTo("password",password);
        return userMapper.selectOneByExample(example);
    }

    public Integer registerUser(User user){
        return userMapper.insert(user);
    }

    public Integer deleteUser(Long id){
        return userMapper.deleteByPrimaryKey(id);
    }

    public Integer updateUser(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public User selectUserById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> selectAllUser(){
        return userMapper.selectAll();
    }

    public User selectUserByUserName(String userName){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName",userName);
        return userMapper.selectOneByExample(example);
    }

    public List<User> selectUserByNickName(String nickName){
        Example example = new Example(User.class);
        example.createCriteria().andLike("nickName","%"+nickName+"%");
        return userMapper.selectByExample(example);
    }

    public Integer countAllUser(){
        Example example = new Example(User.class);
        return userMapper.selectCountByExample(example);
    }

    public Integer countByNickName(String nickName){
        Example example = new Example(User.class);
        example.createCriteria().andLike("nickName","%"+nickName+"%");
        return userMapper.selectCountByExample(example);
    }
}
