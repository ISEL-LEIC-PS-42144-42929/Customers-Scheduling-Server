package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Store;
import com.customersscheduling.DTO.StoreServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreServicesRepository  extends JpaRepository<StoreServices, Integer> {

    @Override
    StoreServices save(StoreServices entity);

}