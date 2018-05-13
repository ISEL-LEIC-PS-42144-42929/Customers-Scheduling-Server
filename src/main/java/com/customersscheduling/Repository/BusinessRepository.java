package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Integer> {

    @Override
    Business save(Business entity);

}
