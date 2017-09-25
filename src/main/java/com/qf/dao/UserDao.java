package com.qf.dao;

import com.qf.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserDao {

    //登录
    public User login(User user);

    //分页总数
    public int count();

    //获取所有用户
    public List<User> getAllUsers(Map<String, Integer> data);

    //添加用户
    void adduser(User user);

    //根据id删除用户
    void deleteUser2(int id);

    //删除用户
    void deleteUser(ArrayList<Integer> ids);

    //更新用户
    void editUser(User user);

    //添加用户权限
    void addUserPerm(HashMap<String, Object> map);

    void deletep(int userid);
}
