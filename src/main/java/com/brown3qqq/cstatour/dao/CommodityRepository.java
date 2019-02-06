package com.brown3qqq.cstatour.dao;

import com.brown3qqq.cstatour.pojo.Column;
import com.brown3qqq.cstatour.pojo.Commodity;
import org.springframework.data.repository.CrudRepository;

public interface CommodityRepository extends CrudRepository<Commodity,String> {
}
