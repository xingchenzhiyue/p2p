package com.qf.controller;

import com.qf.dao.UserDao;
import com.qf.entity.User;
import com.qf.service.UserResService;
import com.qf.util.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
 * \* User: yunwenbo
 * \* Date: 2017/8/22
 * \* Time: 14:42
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
public class UserController {
    @Resource
    private UserDao ud;

    @Resource
    private UserResService urs;

    @RequestMapping("toLogin.do")
    public String toLogin(){
        return "redirect:login.html";
    }
    @RequestMapping("login.do")
    public String login(User user, HttpSession session){
        System.out.println(user);
        String s = SecurityUtil.md5Encrpt(user.getPwd());
        user.setPwd(s);

        //主体
        Subject subject = SecurityUtils.getSubject();
        //令牌
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(),user.getPwd());
        subject.login(token);
//        PrincipalMap principal = subject.getPrincipal();
        user.setId(39);
//
//        //will the user into shiro session
        session.setAttribute("user",ud.login(user));

       // System.out.println(user);
        return "main";
    }

//    login out
    @RequestMapping("loginOut.do")
    public void loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

//    no permission to access
    @RequestMapping("toNoQX.do")
    public String toNoQX(){
        return "redirect:noqx.jsp";
    }

    @RequestMapping("login2.do")
    public String login2(User user, HttpSession session){

        //System.out.println(user.getPwd());
        String s = SecurityUtil.md5Encrpt(user.getPwd());
        user.setPwd(s);

        User u = ud.login(user);
        if(u != null) {
            session.setAttribute("user", u);
        }
        return "main";
    }

    @RequestMapping("getAllUsers.do")
    @RequiresPermissions(value={"user:query"})   //to protected the service interface
    //@RequiresRoles("admin")   rarely used
    @ResponseBody
    public List<User> getAllUsers(int page, int size){
        Map<String,Integer> data = new HashMap<String, Integer>();
        //将页码转换为起点
        data.put("start",(page-1)*size);
        data.put("size2",size);
        List<User> users = ud.getAllUsers(data);
        //String s = JSON.toJSONString(users);
        //System.out.println(s);
        return users;

    }

    @RequestMapping("getCount.do")
    @ResponseBody
    public int getCount(){
        return ud.count();
    }



    @RequestMapping("adduser.do")
    @ResponseBody
    public String adduser(User user){
        //System.out.println(user.getId()+"******");
        if(user.getId() == 0){
            String s = SecurityUtil.md5Encrpt(user.getPwd());
            user.setPwd(s);
            ud.adduser(user);
        }else{
            //只修改账号
            ud.editUser(user);
        }

        return "1";
    }

    //删除多个用户
    @RequestMapping("deleteUser.do")
    @ResponseBody
    public String deleteUser(@RequestBody ArrayList<Integer> ids){
        //System.out.println(ids.size());
        ud.deleteUser(ids);
        return "1";
    }


    //删除一个用户
    @RequestMapping("deleteUser2.do")
    @ResponseBody
    public String deleteUser2(int id){

        ud.deleteUser2(id);

        return "1";
    }

    @RequestMapping("addUserPerm.do")
    @ResponseBody
    public String addUserPerm(@RequestBody ArrayList<Integer> ids){

        try {
            urs.addUserPerm(ids);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }

    }




}