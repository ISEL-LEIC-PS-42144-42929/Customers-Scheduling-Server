package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Store;
import com.customersscheduling.Domain.StoreServices;
import com.customersscheduling.Service.StoreService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreServicesRepository  extends JpaRepository<StoreServices, Integer> {

    @Override
    StoreServices save(StoreServices entity);

    List<StoreServices> findByPk_Store(Store store);
}