package com.customersscheduling.Repository;

import com.customersscheduling.DTO.BusinessDto;
import com.customersscheduling.HALObjects.BusinessHAL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<BusinessDto, Integer> {

    @Override
    BusinessDto save(BusinessDto entity);

}