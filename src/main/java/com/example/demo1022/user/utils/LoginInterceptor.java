package com.example.demo1022.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;


import com.example.demo1022.user.entity.TUser;
import com.example.demo1022.user.service.TUserService;





import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


//Token验证
@Slf4j
@Component
public class LoginInterceptor  implements HandlerInterceptor {
    /**
     * 目标方法执行前
     * 该方法在控制器处理请求方法前执行，其返回值表示是否中断后续操作
     * 返回 true 表示继续向下执行，返回 false 表示中断后续操作
     *
     * @return
     */
    @Resource
    private TUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //检查方法是否有passtoken注解，有则跳过认证，直接通过
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String account;
                try {
                    account = JWT.decode(token).getClaim("account").asString();
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("token不正确，请不要通过非法手段创建token");
                }
                TUser user = new TUser();
                user.setAccount(account);

                //查询数据库，看看是否存在此用户，方法要自己写
                TUser ver = userService.getUserByAccount(user);


                if (ver == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }

                // 验证 token
                if (TokenUtils.verify(token)) {
                    return true;
                } else {
                    throw new RuntimeException("token过期或不正确，请重新登录");
                }

            }
        }
        throw new RuntimeException("没有权限注解一律不通过");
    }




    /**
     * 目标方法执行后
     * 该方法在控制器处理请求方法调用之后、解析视图之前执行
     * 可以通过此方法对请求域中的模型和视图做进一步修改
     */
    @Override
    public void postHandle (HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
        System.out.println("postHandle执行{}");

    }
    /**
     * 页面渲染后
     * 该方法在视图渲染结束后执行
     * 可以通过此方法实现资源清理、记录日志信息等工作
     */
    @Override
    public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {
        System.out.println("afterCompletion执行异常");

    }

}

