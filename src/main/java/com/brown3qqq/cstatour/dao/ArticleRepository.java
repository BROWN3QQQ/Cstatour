package com.brown3qqq.cstatour.dao;

import com.brown3qqq.cstatour.pojo.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,String> {
}
