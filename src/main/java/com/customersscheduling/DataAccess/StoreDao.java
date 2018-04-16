package com.customersscheduling.DataAccess;

import com.customersscheduling.DTO.StoreDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StoreDao implements IStoreDao{

    DataSource dataSource;

    public StoreDao(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(StoreDto store) {

    }

    @Override
    public StoreDto getById(int id) {
        return null;
    }

    @Override
    public StoreDto getByPostalCode(String pc) {
        return null;
    }

    @Override
    public List<StoreDto> getByStreet(String street) {
        return null;
    }

    @Override
    public List<StoreDto> getByLocality(String local) {
        return null;
    }

    @Override
    public boolean delete(StoreDto store) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(StoreDto store) {
        return false;
    }
}
