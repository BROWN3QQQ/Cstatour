package com.brown3qqq.cstatour.dao;

import com.brown3qqq.cstatour.pojo.Restaurant;
import com.brown3qqq.cstatour.pojo.Spot;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant,String> {
}
