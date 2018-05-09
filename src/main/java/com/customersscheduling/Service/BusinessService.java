package com.customersscheduling.Service;

import com.customersscheduling.DTO.BusinessDto;
import com.customersscheduling.HALObjects.BusinessHAL;
import com.customersscheduling.Repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService implements IBusinessService {

    @Autowired
    BusinessRepository repo;

    @Override
    public BusinessHAL insertBusiness(BusinessDto business) {
        repo.save(business);
        return null;
    }
}
