package com.qf.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 尹志迪 on 2017/9/12.
 */
//@Configuration
//@EnableCaching
public class ShiroConfig {
    @Bean
    public JdbcRealm createRealm(){
        JdbcRealm realm=new JdbcRealm();
        realm.setPermissionsLookupEnabled(true);
        realm.setAuthenticationQuery("select password from user where username=?");
        realm.setUserRolesQuery("select roles_name from roles_user left join user u using(id) left join roles using(rid) where u.username=?");
        realm.setPermissionsQuery("select perms from r_u rr left join roles r on rr.id=r.rid left join res re on rr.rid=re.id where r.roles_name=?");
        return realm;
    }
    @Bean
    public CacheManager createCM(){
        return new MemoryConstrainedCacheManager();
    }
    @Bean
    public DefaultWebSecurityManager createSM(JdbcRealm realm,CacheManager cm){
        DefaultWebSecurityManager dsm=new DefaultWebSecurityManager();
        dsm.setRealm(realm);
        dsm.setCacheManager(cm);
        return dsm;
    }
    @Bean
    public ShiroFilterFactoryBean createFilter(DefaultWebSecurityManager sm){
        ShiroFilterFactoryBean fb=new ShiroFilterFactoryBean();
        fb.setSecurityManager(sm);
        fb.setLoginUrl("login.html");
        Map<String,String> s=new HashMap<>();
        s.put("/login.html","anon");
        s.put("/**","authc");
        fb.setFilterChainDefinitionMap(s);
        return fb;
    }
}
