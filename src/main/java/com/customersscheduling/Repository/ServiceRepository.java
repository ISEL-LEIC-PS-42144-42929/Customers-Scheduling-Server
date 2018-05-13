package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Service;
import com.customersscheduling.DTO.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

    @Override
    Service save(Service entity);

}
