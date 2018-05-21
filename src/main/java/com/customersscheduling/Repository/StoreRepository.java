package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Override
    Store save(Store entity);

    Store findByNif(String nif);

    List<Store> findByOwnerEmail(String email);
}