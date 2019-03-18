package com.yfny.servicehello.controller;

import com.yfny.servicepojo.entity.UserEntity;
import com.yfny.servicehello.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jisongZhou on 2019/2/19.
 **/

@RestController
@RequestMapping("/user")
public class UserController {

//    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
//    @Autowired
//    private UserMapper userMapper;
//
//    @RequestMapping("/getUsers")
//    public List<UserEntity> getUsers() {
//        List<UserEntity> users=userMapper.getAll();
//        return users;
//    }
//
//    @RequestMapping("/getUser")
//    public UserEntity getUser(Long id) {
//        UserEntity user=userMapper.getOne(id);
//        return user;
//    }
//
//    @RequestMapping("/add")
//    public void save(UserEntity user) {
//        userMapper.insert(user);
//    }
//
//    @RequestMapping(value="update")
//    public void update(UserEntity user) {
//        userMapper.update(user);
//    }
//
//    @RequestMapping(value="/delete/{id}")
//    public void delete(@PathVariable("id") Long id) {
//        userMapper.delete(id);
//    }


    @Autowired
    private UserServiceImpl userService;


    /**
     * 根据id查看用户
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserById")
    @ResponseBody
    public UserEntity getUserById(@RequestParam(value = "id") long id) {
        UserEntity user = userService.getUserById(id);
        return user;
    }

    /**
     * 带分页的查询
     *
     * @param pageNum  分页数
     * @param pageSize 每页显示的条数
     * @return
     */
    @GetMapping(value = "/findAllUser/{pageNum}/{pageSize}")
    @ResponseBody
    public List<UserEntity> findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }

//    /**
//     * 添加用户
//     *
//     * @param entity
//     * @return
//     */
//    @PostMapping(value = "/addUser")
//    @ResponseBody
//    public boolean addUser(UserEntity entity) {
//        return userService.addUser(entity);
//    }


    /**
     * 添加用户
     * @return
     */
    @PostMapping(value = "/addUser")
    @ResponseBody
    public boolean addUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(123412L);
        userEntity.setPassWord("ff");
        userEntity.setUserName("gg");
        return userService.addUser(userEntity);
    }



    /**
     * 更新用户
     *
     * @param entity
     * @return
     */
    @PostMapping(value = "/updateUser")
    @ResponseBody
    public boolean updateUser(UserEntity entity) {
        return userService.updateUserById(entity);
    }


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteById")
    @ResponseBody
    public boolean deleteById(@RequestParam(value = "id") long id) {
        return userService.deleteUserById(id);
    }


    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping(value = "/login")
    @ResponseBody
    public UserEntity login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        //密码用MD5加密操作，省略...
        return userService.isLogin(username, password);
    }


}
