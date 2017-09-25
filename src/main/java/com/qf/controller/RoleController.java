package com.qf.controller;

import com.qf.dao.RoleDao;
import com.qf.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* Date: 2017/8/24
 * \* Time: 14:37
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
@Mapper
public class RoleController {

    @Resource
    private RoleDao rd;

    @RequestMapping("getAllRoles.do")
    @ResponseBody
    public List<Role> getAllRoles(int page, int size){
        Map<String,Integer> data = new HashMap<String, Integer>();
        data.put("start",(page-1)*size);
        data.put("size2",size);
        System.out.println(data);

        List<Role> roles = rd.getAllRoles(data);
        //System.out.println(roles);
        return roles;
    }
    @RequestMapping("getCounts.do")
    @ResponseBody
    public int getCounts(){
        return rd.getCounts();
    }


    @RequestMapping("addRole.do")
    @ResponseBody
    public String addRole(Role role){
        if(role.getRid() == 0){
            rd.addRole(role);
        }else {
            rd.editRole(role);
        }
        return "1";
    }
    @RequestMapping("deleteRoles.do")
    @ResponseBody
    public String deleteRoles(@RequestBody ArrayList<Integer> ids){

        rd.deleteRoles(ids);
        return "1";

    }

}