package com.example.demo1022.user.controller;


import com.alibaba.druid.util.StringUtils;
import com.example.demo1022.user.entity.TUser;
import com.example.demo1022.user.entity.TUserDto;
import com.example.demo1022.user.service.TUserService;
import com.example.demo1022.user.utils.EmailService;
import com.example.demo1022.user.utils.JWTUtils;
import com.example.demo1022.user.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author user
 * @since 2023-11-23
 */
@RestController
@RequestMapping("/t-user")
public class TUserController {
    @Autowired
    private TUserService userService;

    //    返回所有书本
    @GetMapping("/allUser")
    public List<TUser> findAllUser(){
        return userService.list();
    }


    //添加用户
    @PostMapping("/addUser")
    public Boolean addUser(@RequestBody TUser user){
        System.out.println(user);
//
//        user.setCreateTime(LocalDateTime.now());
//        user.setUpdateTime(LocalDateTime.now());
        TUser reg = userService.getUserByAccount(user);

        //判断用户名是否已存在
        if (reg == null) {
            System.out.println(user);
            return userService.save(user);
        } else {
            return false;
        }
    }

    //验证用户是否存在
    @GetMapping("/userVerify")
    public Boolean userVerify(TUser user){
        TUser ver = userService.getUserByAccount(user);

        if(ver==null){
            return false;
        }else {
            return true;

        }

    }
    //邮箱验证

    @Autowired
    private EmailService emailService;

    //发送指定邮箱
    @GetMapping("/register")
    public String sendCode(String to, HttpSession httpSession) {
        String randomCode= emailService.email(to);
        httpSession.setAttribute("code", randomCode);

//        return "验证码已发送到指定邮箱" + randomCode;
        return randomCode;
    }

    //验证邮箱
    @GetMapping("/checkCode")
    public String checkCode(String code, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "验证码未发送";
        }
        String Code = (String) session.getAttribute("code");
        if (code.equals(Code)) {
            return "验证码正确";
        }
        return "验证码错误";
    }



    @GetMapping("/delete/{id}")
    public Boolean userDelete(@PathVariable Long id) {
        return userService.removeById(id);
    }

    @PostMapping("/update")
    public Boolean userUpdate(@RequestBody TUser user) {
        return userService.updateById(user);
    }

    @GetMapping("/get/{id}")
    public TUser userGet(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/list")
    public List<TUser> list() {
        return userService.list();
    }


    //根据用户名返回用户信息
    @PostMapping("/loginAccount")
    public TUserDto loginAccount(@RequestBody TUser user){
        System.out.println(user);
        TUser loginUser = userService.getUserByAccount(user);

        String JWTToken = JWTUtils.getJwtToken(Long.valueOf(loginUser.getId()), user.getAccount());

        //登录成功状态处理
        TUserDto tUserDto = new TUserDto(loginUser.getId(),loginUser.getAccount(),loginUser.getNickname(),loginUser.getLikes(),loginUser.getViews(),loginUser.getPortrait(),loginUser.getCollect());

        tUserDto.setToken(JWTToken);
        tUserDto.setState("登录成功！");
//            return JWTToken;
        return tUserDto;

    }


    //登录
    @PostMapping("/login")
    public TUserDto login(@RequestBody TUser user){

        System.out.println(user);




        //错误状态处理
        TUserDto userA=new TUserDto();


        // 判空
        if (user == null || StringUtils.isEmpty(user.getAccount()) ||
                StringUtils.isEmpty(user.getPassword())){
//            return "传过来的用户名或密码为空";
            userA.setState("账号和密码不能为空！");
            return  userA;
        }

        TUser userAccount = userService.getUserByAccount(user);

        if(userAccount == null){
            userA.setState("用户名不存在！");
            return userA;
        }else {

            // 根据用户名、密码查询数据
            TUser loginUser = userService.getUserByAccountAndPassword(user);

            System.out.println("查到的数据：：：：：：" + loginUser);

            if (loginUser == null){
//            return "用户名或密码错误";
                userA.setState("密码错误！");

                return userA;

            }else if (loginUser != null){

//                System.out.println("=========" + String.valueOf(user.getId())+loginuser.getId());
                // 生成token
//                String JWTToken = JWTUtils.getJwtToken(Long.valueOf(loginUser.getId()), user.getAccount());

                String JWTToken = TokenUtils.token(loginUser.getAccount(),loginUser.getPassword());
                //登录成功状态处理
                TUserDto tUserDto = new TUserDto(loginUser.getId(),loginUser.getAccount(),loginUser.getNickname(),loginUser.getLikes(),loginUser.getViews(),loginUser.getPortrait(),loginUser.getCollect());

                tUserDto.setToken(JWTToken);
                tUserDto.setState("登录成功！");
//            return JWTToken;
                return tUserDto;
            }

        }




        return null;
    }


    //异步上传
    @PostMapping("/ajaxUploadFile")
    @ResponseBody
    public Map ajaxUploadFile(MultipartFile[] files){

        Map<String,Object> map=new HashMap<>();
        for(MultipartFile file:files){
            //获取文件名以及后缀名
            String fileName=file.getOriginalFilename();

            //获取jar包所在目录
            ApplicationHome h = new ApplicationHome(getClass());
            File jarF = h.getSource();
            //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
            String dirPath = jarF.getParentFile().toString()+"/upload/";
            System.out.println(dirPath);



            File filePath=new File(dirPath);
            if(!filePath.exists()){
                filePath.mkdirs();
            }
            try{
                //将文件写入磁盘
                file.transferTo(new File(dirPath+fileName));
                //文件上传成功返回状态信息
                map.put("msg","上传成功！");
                map.put("url",fileName);
                map.put("code",200);

            }catch (Exception e){
                e.printStackTrace();
                //上传失败，返回失败信息
                map.put("msg","上传失败！");
            }
        }
        //携带上传状态信息回调到文件上传页面
        return map;
    }


}

