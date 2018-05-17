package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Override
    Store save(Store entity);

    @Query("select s from Store s where s.id.business.NIF=:nif")
    List<Store> findByNif(@Param("nif") int nif);

}