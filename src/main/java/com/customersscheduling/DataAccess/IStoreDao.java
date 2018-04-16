package com.customersscheduling.DataAccess;

import com.customersscheduling.DTO.StoreDto;

import java.util.List;

public interface IStoreDao {

    void insert(StoreDto store);
    StoreDto getById(int id);
    StoreDto getByPostalCode(String pc);
    List<StoreDto> getByStreet(String street);
    List<StoreDto> getByLocality (String local);
    boolean delete(StoreDto store);
    boolean delete(int id);
    boolean update(StoreDto store);
}
