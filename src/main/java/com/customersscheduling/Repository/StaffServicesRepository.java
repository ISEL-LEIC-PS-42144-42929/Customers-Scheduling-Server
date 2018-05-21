package com.customersscheduling.Repository;

import com.customersscheduling.DTO.StaffServices;
import com.customersscheduling.DTO.StaffServicesPK;
import com.customersscheduling.DTO.StoreServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffServicesRepository extends JpaRepository<StaffServices, StaffServicesPK> {

    @Override
    StaffServices save(StaffServices entity);

}