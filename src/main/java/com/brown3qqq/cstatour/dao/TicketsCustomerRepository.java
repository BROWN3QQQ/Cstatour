package com.brown3qqq.cstatour.dao;

import com.brown3qqq.cstatour.pojo.Ticket;
import com.brown3qqq.cstatour.pojo.User;


public interface TicketsCustomerRepository {
    Ticket get(String ticket);

}
