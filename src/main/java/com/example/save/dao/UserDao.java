package com.example.save.dao;

import com.example.save.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public User getUserByMassage(@Param("username") String username,@Param("password") String password);
    public List<User> getAllUser(@Param("username") String username,@Param("pageStart") int PageStart, @Param("pageSize")int PageSize);
    public int getUserCounts(@Param("username") String username);
    public int updateState(Integer id,Boolean state);
    public int addUser(User user);
    public int deleteUser(Integer id);
    public User getUpdateUser(Integer id);
    public int editUser(User user);
}