package com.qf.dao;

import com.qf.entity.Res;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface ResDao {

    //返回所有模块
    List<Res> findAllRes(int id);

    //查找所有权限
    List<Res> findAllResForPerm();

//    分页
    List<Res> findAllRess(Map<String, Integer> map);
    int getCount();


    //为用户增加权限
    void addUserPerm(ArrayList<Integer> urids);

    //add res
    void addRes(Res res);
    //edit res
    void editRes(Res res);
    //delete res

    void deleteRes(ArrayList<Integer> ids);


}
