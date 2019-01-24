package com.brown3qqq.cstatour.service;

import com.brown3qqq.cstatour.dao.Impl.UserRepositoryimpl;
import com.brown3qqq.cstatour.dao.UserRepository;
import com.brown3qqq.cstatour.pojo.Ticket;
import com.brown3qqq.cstatour.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sound.midi.SoundbankResource;
import java.util.*;

@Service
public class userService {

    Random random = new Random();
    @Autowired
    UserRepository userRepository;


    UserRepositoryimpl userRepositoryimpl;
    //注册
    public Map<String,String > register(String userName,String password,String telnum){

        Map<String,String> map = new HashMap<String, String >();
        if(StringUtils.isEmpty(userName)){
            map.put("msg", "用户名不能为空");
            return map;
        }

        if(StringUtils.isEmpty(password)){
            map.put("msg","密码不能为空");
            return  map;
        }


        User user = userRepositoryimpl.getuser(userName);
        if(user != null){
            map.put("msg","用户名已被注册");
            return  map;
        }

        User newuser = new User();

        newuser.setRealname(userName);
        newuser.setPasswd(password);
        newuser.setTelnum(telnum);

        userRepository.save(newuser);

        //注册完成下发ticket之后自动登录
        String ticket = addLoginTicket(userRepositoryimpl.getuser(userName).getId());
        map.put("ticket",ticket);

        return map;
    }
    public static String addLoginTicket(String user_id){

        Ticket ticket = new Ticket();
        Date nowDate = new Date();
        nowDate.setTime(3600*24*100 + nowDate.getTime());
        ticket.setExpired(nowDate);
        ticket.setStatus(0);

        ticket.setTicket(UUID.randomUUID().toString().replaceAll("_",""));

        return ticket.getTicket();

    }

//    public static void main(String[] args) {
//        String TEST = addLoginTicket("98237473828379");
//        System.out.println(TEST);
//    }
}
