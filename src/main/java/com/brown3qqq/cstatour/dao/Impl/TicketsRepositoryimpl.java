package com.brown3qqq.cstatour.dao.Impl;

import com.brown3qqq.cstatour.dao.TicketsCustomerRepository;
import com.brown3qqq.cstatour.pojo.Ticket;
import com.brown3qqq.cstatour.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.Iterator;
import java.util.List;

@Repository
public class TicketsRepositoryimpl implements TicketsCustomerRepository {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public Ticket get(String ticket) {
        try {
            Query query = new Query(Criteria.where("ticket").is(ticket));
            List<Ticket> userlist = mongoTemplate.find(query,Ticket.class,"ticket");
            Iterator<Ticket> it = userlist.iterator();
            Ticket realticket = it.next();

            return realticket;
        }catch (Exception e){

        }

        return null;
    }
}
