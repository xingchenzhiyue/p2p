package com.qf.controller;


import com.qf.dao.ResDao;
import com.qf.entity.Res;
import com.qf.entity.User;
import com.qf.service.ResourceService;
import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* Date: 2017/8/23
 * \* Time: 16:52
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
@Mapper
public class ResController {

 @Resource
    private ResDao rd;

    @Resource
    private ResourceService rse;

    //所有模块
    @RequestMapping("findAllRes.do")
    @ResponseBody
    public List<Res> findAllRes(HttpSession session){
        //取出当前登录的用户
        User user = (User) session.getAttribute("user");
        System.out.println(user+"---------------------------------");
        List<Res> res = rd.findAllRes(user.getId());
        //System.out.println(res.size()+"-----------");
        return res;
    }

//    find all resources
    @RequestMapping("findAllResForPerm.do")
    @ResponseBody
    public List<Res> findAllResForPerm(){
        List<Res> rs = rd.findAllResForPerm();
        return rs;
    }

    @RequestMapping(value="findAllResForPerm2.do")
    @ResponseBody
    public Map<String,Object> findAllResForPerm2(){
        List<Res> data = rd.findAllResForPerm();
        //System.out.println(data);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("rows",data);
        return map;
    }
    @RequestMapping(value="findAllRess.do")
    @ResponseBody
    public Map<String,Object> findAllRess(int page,int size){
//        System.out.println(page+"ppppppp");
//        System.out.println(size+"ssssssssss");
        Map<String,Integer> data = new HashMap<String, Integer>();
        //将页码转换为起点
        data.put("start",(page-1)*size);
        data.put("size2",size);
        List<Res> data2 = rd.findAllRess(data);
        //System.out.println(data);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("rows",data2);
        return map;
    }
    @RequestMapping("getCount3.do")
    @ResponseBody
    public int getCount(){
        System.out.println(rd.getCount()+"*********");
        return rd.getCount();
    }


    @RequestMapping("addRes2.do")
    @ResponseBody
    public String addRes(Res res){
        System.out.println("1111");
        rd.addRes(res);
        System.out.println("2222");
        return "1";
    }

    @RequestMapping("editRes.do")
    @ResponseBody
    public String editRes(Res res){
        System.out.println(res);
        rd.editRes(res);
        return "1";
    }

    ArrayList<Integer> ids2 = new ArrayList<Integer>();
    @RequestMapping("deleteRes.do")
    @ResponseBody
    public String deleteRes(int id){
        ids2.add(id);
        List<Res> ress = rd.findAllResForPerm();
        for (Res r:ress) {
            if(r.getPid()==id){
                id=r.getId();
                deleteRes(r.getId());
            }
        }
        rd.deleteRes(ids2);
        return "1";
    }

}