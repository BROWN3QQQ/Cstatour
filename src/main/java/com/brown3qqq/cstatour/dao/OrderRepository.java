package com.brown3qqq.cstatour.dao;

import com.brown3qqq.cstatour.pojo.CommdityInfo;
import com.brown3qqq.cstatour.pojo.Kind;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<CommdityInfo,String> {
}
