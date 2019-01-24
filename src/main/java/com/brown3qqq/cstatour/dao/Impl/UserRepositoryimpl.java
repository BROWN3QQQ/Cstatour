package com.brown3qqq.cstatour.dao.Impl;

import com.brown3qqq.cstatour.dao.UserRepository;
import com.brown3qqq.cstatour.pojo.User;

import java.util.Iterator;

public class UserRepositoryimpl {

    UserRepository userRepository;

    public User getuser(String name){

        Iterable<User> userIterable =  userRepository.findAll();
        Iterator<User> it = userIterable.iterator();

        while (it.hasNext()){

            if (it.next().getRealname() == name){
                User user = it.next();
                return user;
            }

        }
        return null;
    }
}
