package com.brown3qqq.cstatour.dao.Impl;

import com.brown3qqq.cstatour.dao.UserCustomerRepository;
import com.brown3qqq.cstatour.dao.UserRepository;
import com.brown3qqq.cstatour.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public class UserRepositoryimpl implements UserCustomerRepository {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public User getUser(String name) {

        try {
            Query query = new Query(Criteria.where("realname").is(name));
            List<User> userlist = mongoTemplate.find(query,User.class,"user");
            Iterator<User> it = userlist.iterator();
            User user = it.next();

            return user;
        }catch (Exception e){

        }

        return null;
    }


}
