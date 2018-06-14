package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Override
    Store save(Store entity);

    Store findByNif(String nif);

    List<Store> findByOwnerEmail(String email);
}