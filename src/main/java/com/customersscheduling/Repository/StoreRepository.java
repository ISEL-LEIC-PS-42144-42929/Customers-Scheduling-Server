package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Override
    Store save(Store entity);

}