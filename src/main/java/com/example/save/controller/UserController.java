package com.example.save.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.save.bean.QueryInfo;
import com.example.save.bean.User;
import com.example.save.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/allUser")
    public String getUserList(QueryInfo queryInfo){

        //获取最大列表数和当前编号
        int pageStart = (queryInfo.getPageNum()-1) * queryInfo.getPageSize();
        int userCounts = userDao.getUserCounts("%" + queryInfo.getQuery() + "%");
        List<User> users = userDao.getAllUser("%" + queryInfo.getQuery() + "%",pageStart,queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("userCounts",userCounts);
        res.put("data",users);
//        System.out.println(users);
        String user_json = JSON.toJSONString(res, SerializerFeature.WriteNullBooleanAsFalse);
//        System.out.println(user_json);
        return user_json;
    }

    @RequestMapping("/userstate")
    public String updateUserState(@RequestParam("id") Integer id,@RequestParam("state") Boolean state){
        int i = userDao.updateState(id,state);
        System.out.println("编号"+id);
        System.out.println("状态"+state);
        String str = i > 0 ? "success":"error";
        return str;
    }

    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user){
        user.setRole("普通用户");
        user.setState(false);
        int i = userDao.addUser(user);
        return i > 0?"success":"error";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(Integer id){
        int i = userDao.deleteUser(id);
        return i > 0 ?"success":"error";
    }

    @RequestMapping("/getupdate")
    public String getUpdateUser(Integer id){
        User user = userDao.getUpdateUser(id);
        String string = JSON.toJSONString(user);
        return string;
    }

    @RequestMapping("/edituser")
    public String editUser(@RequestBody User user){
        int i = userDao.editUser(user);
        return i > 0 ?"success":"error";
    }
}
