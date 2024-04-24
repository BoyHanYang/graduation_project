package com.gym.config.security.detailservice;

import com.gym.config.security.exception.CustomerAuthenionException;
import com.gym.web.member.entity.Member;
import com.gym.web.member.service.MemberService;
import com.gym.web.sys_menu.entity.SysMenu;
import com.gym.web.sys_menu.service.SysMenuService;
import com.gym.web.sys_user.entity.SysUser;
import com.gym.web.sys_user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author yangbohan
 * @Date 2024/4/24 16:55
 */

@Component("custometUserDetailService")
public class CustometUserDetailService implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //admin:2  ls:1
        int index = s.indexOf(":");
        String username = s.substring(0, index);
        String userType = s.substring(index + 1);
        //认证:登录
        if (userType.equals("1")) {
            Member user = memberService.loadUser(username);
            if (user == null) {
                throw new CustomerAuthenionException("用户名或密码错误!");
            }
            //授权:把该用户拥有的按钮权限，交给spring secuity进行管理
            //获取会员的按钮权限
            List<SysMenu> menuList = sysMenuService.getMenuByMemberId(user.getMemberId());
            //取出code字段
            List<String> collect =
                    menuList.stream().map(item -> item.getCode()).filter(item -> item != null && StringUtils.isNotEmpty(item)).collect(Collectors.toList());
            String[] strings = collect.toArray(new String[collect.size()]);
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(strings);
            //授权
            user.setAuthorities(authorityList);
            return user;
        } else if (userType.equals("2")) {
            SysUser user = sysUserService.loadUser(username);
            if (user == null) {
                throw new CustomerAuthenionException("用户名或密码错误!");
            }
            //授权:把该用户拥有的按钮权限，交给spring secuity进行管理
            List<SysMenu> menuList = null;
            if(StringUtils.isNotEmpty(user.getIsAdmin()) && user.getIsAdmin().equals("1")){
                menuList = sysMenuService.list();
            }else{
                menuList = sysMenuService.getMenuByUserId(user.getUserId());
            }
            //取出code字段
            List<String> collect =
                    menuList.stream().map(item -> item.getCode()).filter(item -> item != null && StringUtils.isNotEmpty(item)).collect(Collectors.toList());
            String[] strings = collect.toArray(new String[collect.size()]);
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(strings);
            //授权
            user.setAuthorities(authorityList);
            return user;
        } else {
            throw new CustomerAuthenionException("用户类型错误!");
        }
    }
}