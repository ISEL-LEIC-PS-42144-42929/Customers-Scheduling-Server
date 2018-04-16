package com.customersscheduling.DataAccess;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;

public class StaffDao implements IStaffDao {

    DataSource dataSource;

    public StaffDao(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
