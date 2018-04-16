package com.customersscheduling.DataAccess;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;

public class BusinessDao implements IBusinessDao{

    DataSource dataSource;

    public BusinessDao(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
