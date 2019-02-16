package com.brown3qqq.cstatour.service;


import com.brown3qqq.cstatour.dao.Impl.TicketsRepositoryimpl;
import com.brown3qqq.cstatour.dao.Impl.UserRepositoryimpl;
import com.brown3qqq.cstatour.dao.TicketRepository;
import com.brown3qqq.cstatour.dao.TicketsCustomerRepository;
import com.brown3qqq.cstatour.dao.UserRepository;
import com.brown3qqq.cstatour.pojo.Ticket;
import com.brown3qqq.cstatour.pojo.User;
import com.brown3qqq.cstatour.pojo.Admin.adminname;
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

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepositoryimpl userRepositoryimpl;

    @Autowired
    TicketsRepositoryimpl ticketsRepositoryimpl;



    //注册
    public  Map<String,String > register(String userName,String password,String telnum){

        Map<String,String> map = new HashMap<String, String >();
        if(StringUtils.isEmpty(userName)){
            map.put("msg", "用户名不能为空");
            return map;
        }

        if(StringUtils.isEmpty(password)){
            map.put("msg","密码不能为空");
            return  map;
        }

        User user = userRepositoryimpl.getUser(userName);

        if(user != null){
            map.put("msg",user.getId());
            return  map;
        }
//
        User newuser = new User();

        newuser.setRealname(userName);
        newuser.setPasswd(password);
        newuser.setTelnum(telnum);

        userRepository.save(newuser);

        //注册完成下发ticket之后自动登录
        String ticket = addLoginTicket(userName,0);
        map.put("ticket",ticket);

        return map;
    }
    //登录
    public  Map<String,Object > login(String userName,String password){

        Map<String,Object> map = new HashMap<String,Object>();

        if(StringUtils.isEmpty(userName)){
            map.put("msg", "用户名不能为空");
            return map;
        }

        if(StringUtils.isEmpty(password)){
            map.put("msg","密码不能为空");
            return  map;
        }

        User user = userRepositoryimpl.getUser(userName);

        if(user == null){
            map.put("msg","用户不存在");
            return  map;
        }

        if (!user.getPasswd().equals(password)) {
            map.put("msg", "密码错误");
            map.put("get",user.getPasswd());
            map.put("psd",password);
            return map;
        }

        String ticket;
        if (userName.equals(adminname.ADMINNAME)){
            ticket = addLoginTicket(userName,1);
        }else{
            ticket = addLoginTicket(userName,1);
        }

    //注册完成下发ticket之后自动登录

        map.put("ticket",ticket);

        return map;
    }
    //更新用户
    public Map<String,String> update(String userName,String password,String newpassword){
        Map<String,String> map = new HashMap<String, String >();
        if(StringUtils.isEmpty(userName)){
            map.put("msg", "用户名不能为空");
            return map;
        }

        if(StringUtils.isEmpty(password)){
            map.put("msg","密码不能为空");
            return  map;
        }

        User user = userRepositoryimpl.getUser(userName);

        if(user == null){
            map.put("msg","用户不存在");
            return  map;
        }

        user.setPasswd(newpassword);
        userRepository.save(user);

        String ticket;
        if (userName.equals(adminname.ADMINNAME)){
            ticket = addLoginTicket(userName,1);
        }else{
            ticket = addLoginTicket(userName,1);
        }


        map.put("ticket",ticket);
        map.put("msg","更改密码成功");
        return map;
    }
    //添加token
    public  String addLoginTicket(String username,int state){

        User user = userRepositoryimpl.getUser(username);
        Ticket ticket = new Ticket();
        Date nowDate = new Date();
        nowDate.setTime(nowDate.getTime());
        ticket.setId(user.getId());
        ticket.setExpired(nowDate);
        ticket.setStatus(state);

        ticket.setTicket(UUID.randomUUID().toString().replaceAll("_",""));

        ticketRepository.save(ticket);
        return ticket.getTicket();

    }

}
