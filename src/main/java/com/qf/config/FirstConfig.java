package com.qf.config;


import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/12.
 */
@Configuration
public class FirstConfig {

    @Bean
    public JdbcRealm createRealm() {
        System.out.println("----创建了realm对象");
        JdbcRealm realm = new JdbcRealm();
        realm.setPermissionsLookupEnabled(true);
        realm.setAuthenticationQuery("select pwd from users where account=?");
        realm.setUserRolesQuery("select role_name from user_role left join users u using(id) left join roles using(rid) where u.account=?");
        realm.setPermissionsQuery("select perms from role_res left join roles r using(rid) left join res using(id) where r.role_name=?");
        return realm;
    }

    //    @Bean
//    public CacheManager createCM(){
//        return new MemoryConstrainedCacheManager();
//    }
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();

        redisManager.setHost("127.0.0.1");
        redisManager.setPort(6379);
        redisManager.setExpire(1800);// 配置过期时间
        // redisManager.setTimeout(timeout);
        // redisManager.setPassword(password);
        return redisManager;
    }


    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    @Bean
    public DefaultWebSecurityManager reateSM(JdbcRealm realm) {

        DefaultWebSecurityManager dsm = new DefaultWebSecurityManager();
        dsm.setRealm(realm);
        // dsm.setCacheManager(cacheManager());
        return dsm;
    }

    @Bean
    public ShiroFilterFactoryBean createFilter(DefaultWebSecurityManager sm) {
        ShiroFilterFactoryBean fb = new ShiroFilterFactoryBean();
        fb.setSecurityManager(sm);
        fb.setLoginUrl("/login.html");
        Map<String, String> s = new HashMap<>();
        s.put("/login.html", "anon");
        s.put("/**", "anon");
        fb.setFilterChainDefinitionMap(s);
        return fb;
    }
}
