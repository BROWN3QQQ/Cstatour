package com.brown3qqq.cstatour.dao;

import com.brown3qqq.cstatour.pojo.Hotel;
import com.brown3qqq.cstatour.pojo.User;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel,String> {
}
