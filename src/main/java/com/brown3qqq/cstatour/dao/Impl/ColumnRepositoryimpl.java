package com.brown3qqq.cstatour.dao.Impl;

import com.brown3qqq.cstatour.dao.ColumnCustomerRepository;
import com.brown3qqq.cstatour.pojo.Column;
import com.brown3qqq.cstatour.pojo.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public class ColumnRepositoryimpl implements ColumnCustomerRepository {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public Column get(String columnname) {
        try {
            Query query = new Query(Criteria.where("column").is(columnname));
            List<Column> userlist = mongoTemplate.find(query,Column.class,"column");
            Iterator<Column> it = userlist.iterator();
            Column column = it.next();

            return column;
        }catch (Exception e){

        }

        return null;
    }
}
