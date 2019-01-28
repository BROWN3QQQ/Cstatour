package com.brown3qqq.cstatour.dao;

import com.brown3qqq.cstatour.pojo.User;

public interface UserCustomerRepository {
    User getUser(String name);
}
