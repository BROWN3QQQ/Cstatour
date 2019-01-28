package com.brown3qqq.cstatour.dao;

import com.brown3qqq.cstatour.pojo.Column;

public interface ColumnCustomerRepository {
    Column get(String columnname);
}
