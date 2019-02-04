package com.brown3qqq.cstatour.dao.Impl;

import com.brown3qqq.cstatour.dao.ArticleCustomerRepository;
import com.brown3qqq.cstatour.pojo.Article;
import com.brown3qqq.cstatour.pojo.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public class ArticleRepositoryimpl implements ArticleCustomerRepository {
    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public Article get(String name) {
        try {
            Query query = new Query(Criteria.where("article").is(name));
            List<Article> userlist = mongoTemplate.find(query,Article.class,"article");
            Iterator<Article> it = userlist.iterator();
            Article article = it.next();

            return article;
        }catch (Exception e){

        }

        return null;
    }
}
