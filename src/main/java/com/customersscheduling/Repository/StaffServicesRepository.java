package com.customersscheduling.Repository;

import com.customersscheduling.Domain.StaffServices;
import com.customersscheduling.Domain.StaffServicesPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffServicesRepository extends JpaRepository<StaffServices, StaffServicesPK> {

    @Override
    StaffServices save(StaffServices entity);

}