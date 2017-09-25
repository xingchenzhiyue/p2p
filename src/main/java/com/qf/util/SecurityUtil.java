package com.qf.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yunwenbo
 * \* Date: 2017/8/23
 * \* Time: 9:29
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class SecurityUtil {

    //md5加密
    public static String md5Encrpt(String value){
        //stringBuilder线程不安全的(适合单线程多操作)
        StringBuilder sd = new StringBuilder();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(value.getBytes());
            //加密
            byte[] target = md5.digest();
            //System.out.println(digest);

            //转化为string
            for (int i = 0; i < target.length; i++) {
                int x = (int)target[i]&0xff;

                //加盐
                x=x+1;

                if(x<16){
                    sd.append(0);
                }
                sd.append(Integer.toHexString(x));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sd.toString();

    }
}