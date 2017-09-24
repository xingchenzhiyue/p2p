package com.qf.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yunwenbo
 * \* Date: 2017/9/13
 * \* Time: 9:44
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//@Configuration
//@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public HttpMessageConverters createConvert(){
        FastJsonHttpMessageConverter con = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        //beatify the format
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        con.setFastJsonConfig(config);
        // config.setFeatures();
        return new HttpMessageConverters(con);
    }

    // define key generation strategy
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                //use the className+methodName+paramName as a key
                StringBuilder sd = new StringBuilder();
                //class name
                sd.append(o.getClass().getName());
                //method name
                sd.append(method.getName());
                for(Object x:objects){
                    sd.append(x);
                }
                return sd.toString();
            }
        };
    }

    //to generate RedisTemplate
    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(factory);

        //specify the key serialization
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.setHashValueSerializer(new FastJsonSerializer<Object>(Object.class));
        template.afterPropertiesSet();
        return template;
    }

    //to generate cacheManager
    @Bean
    public CacheManager createCM(RedisTemplate<Object,Object> redisTemplate){
        RedisCacheManager cm = new RedisCacheManager(redisTemplate);
       //sets the valid time
        cm.setDefaultExpiration(60);//60s后redis中的数据自动清除
        return cm;
    }

    //使用FastJson实现序列化
    //泛型：以类型作为参数
    class FastJsonSerializer<T> implements RedisSerializer<T>{
        private Class<T> ct;
        public FastJsonSerializer(Class<T> t2){
            this.ct=t2;
        }
        @Override
        public byte[] serialize(T t) throws SerializationException {
            try {
                return JSON.toJSONString(t).getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public T deserialize(byte[] bytes) throws SerializationException {
            try {
                String s = new String(bytes, "utf-8");
                return JSON.parseObject(s,ct);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}