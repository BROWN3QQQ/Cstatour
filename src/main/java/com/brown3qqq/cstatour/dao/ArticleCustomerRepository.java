package com.brown3qqq.cstatour.dao;

import com.brown3qqq.cstatour.pojo.Article;
import com.brown3qqq.cstatour.pojo.Column;

public interface ArticleCustomerRepository {
    Article get(String articlename);
}
