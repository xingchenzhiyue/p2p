package com.qf.service;

import com.qf.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yunwenbo
 * \* Date: 2017/9/8
 * \* Time: 10:27
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class UserResService {

    @Resource
    private UserDao ud;

    @Transactional
    public void addUserPerm(ArrayList<Integer> data){

        int userid=data.get(0);
        ud.deletep(userid);
        //System.out.println("userid"+userid);
        data.remove(0);
        HashMap<String,Object> map = new HashMap<String, Object>();
        //System.out.println(data);
        map.put("userid",userid);
        map.put("rids",data);
        ud.addUserPerm(map);

    }
}