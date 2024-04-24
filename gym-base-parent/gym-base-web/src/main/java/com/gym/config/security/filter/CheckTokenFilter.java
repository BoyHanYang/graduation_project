package com.gym.config.security.filter;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gym.config.security.detailservice.CustometUserDetailService;
import com.gym.config.security.exception.CustomerAuthenionException;
import com.gym.config.security.handler.LoginFailureHandler;
import com.gym.jwt.JwtUtils;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author yangbohan
 * @Date 2024/4/24 18:23
 */

@Data
@Component("checkTokenFilter")
public class CheckTokenFilter extends OncePerRequestFilter {
    @Value("#{'${ignore.url}'.split(',')}")
    private List<String> ignoreUrl = Collections.emptyList();
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CustometUserDetailService custometUserDetailService;
    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //获取请求的url
            String url = request.getRequestURI();
            //判断是否放行
            if (!ignoreUrl.contains(url) && !url.startsWith("/images/")) {
                validateToken(request);
            }
        } catch (AuthenticationException e) {
            loginFailureHandler.commence(request, response, e);
            return;
        }
        filterChain.doFilter(request,response);
    }

    private void validateToken(HttpServletRequest request) {
        //获取token数据
        String token = request.getHeader("token");
        //如果从头部获取token失败，那么从参数获取
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        //如果没有获取到token
        if (StringUtils.isEmpty(token)) {
            throw new CustomerAuthenionException("token获取失败！");
        }
        //token验证
        if(!jwtUtils.verify(token)){
            throw new CustomerAuthenionException("token验证失败！");
        }
        DecodedJWT decodedJWT = jwtUtils.jwtDecode(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        String username = claims.get("username").asString();
        String userType = claims.get("userType").asString();
        //查询用户信息,交给spring security
        UserDetails details = custometUserDetailService.loadUserByUsername(username + ":" + userType);
        //构造token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(details,null,details.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //设置到sping security上下文
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}