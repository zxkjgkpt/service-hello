package com.yfny.servicehello.service;


import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageHelper;
import com.yfny.servicehello.mapper.CarMapper;
import com.yfny.servicehello.mapper.DetailsMapper;
import com.yfny.servicepojo.entity.CarEntity;
import com.yfny.servicepojo.entity.DetailsEntity;
import com.yfny.servicepojo.entity.UserEntity;
import com.yfny.servicehello.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zileShi on 2019/2/19.
 **/
@Service
public class UserServiceImpl{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private CarMapper carMapper;


    public UserEntity getUserById(long userId) {
        return userMapper.selectUserAllById(userId);
    }


    @LcnTransaction //分布式事务注解
    @Transactional
    public boolean addUser(UserEntity record) {
        boolean result = false;
        userMapper.insertSelective(record);
        result = true;
        return result;
    }

    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */

    public List<UserEntity> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }


    /**
     * 删除包含外键
     * @param userId
     * @return
     */
    @Transactional
    public boolean deleteUserById(long userId) {
        boolean result = false;
        DetailsEntity detailsEntity = new DetailsEntity();
        detailsEntity.setUserId(userId);
        detailsMapper.delete(detailsEntity);
        CarEntity carEntity = new CarEntity();
        carEntity.setUserId(userId);
        carMapper.delete(carEntity);
        userMapper.deleteByPrimaryKey(userId);
        result = true;
        return result;
    }


    @Transactional
    public boolean updateUserById(UserEntity record) {
        boolean result = false;
        userMapper.updateByPrimaryKeySelective(record);
        result = true;
        return result;
    }



    public UserEntity isLogin(String username, String password) {
        return userMapper.isLogin(username,password);
    }



}
