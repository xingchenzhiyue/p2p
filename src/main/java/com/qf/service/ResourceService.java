package com.qf.service;

import com.qf.dao.ResDao;
import com.qf.entity.Res;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yunwenbo
 * \* Date: 2017/9/11
 * \* Time: 16:16
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
Description * \
 */
@Service
public class ResourceService {

    @Resource
    private ResDao rd;

    public List<Res> findAllResForPerm(){

        List<Res> res = rd.findAllResForPerm();

        List<Res> res2 = new ArrayList<Res>(res);
        res2.addAll(res);

        List<Res> root = new ArrayList<Res>();

        for(Res a:res){
            if(a.getPid() == 0){
                root.add(a);
            }
            for(Res b:res2){
                if(b.getPid()==a.getId()){
                    String bid=""+b.getId();
                    if(bid.startsWith("3")){
                        a.getI().add("as.html|"+b.getText());
                    }else{
                        a.getI().add(b);
                    }
                }
            }
        }

        return root;
    }


}