package com.qf.dao;

import com.qf.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface RoleDao {

    //获取全部角色信息
    List<Role> getAllRoles(Map<String, Integer> data);

    //获取数据记录总数
    int getCounts();

    //添加角色
    void addRole(Role role);

    //更新角色
    void editRole(Role role);

    //删除角色
    void deleteRoles(ArrayList<Integer> ids);
}
