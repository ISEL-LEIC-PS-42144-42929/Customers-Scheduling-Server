package com.customersscheduling.Service;

import com.customersscheduling.DTO.Business;
import com.customersscheduling.DTO.Store;
import com.customersscheduling.DTO.StoreServices;
import com.customersscheduling.HALObjects.BusinessHAL;
import com.customersscheduling.Repository.BusinessRepository;
import com.customersscheduling.Repository.StoreRepository;
import com.customersscheduling.Repository.StoreServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService implements IBusinessService {

    @Autowired
    BusinessRepository repo;

    @Autowired
    StoreRepository storeRepo;

    @Autowired
    StoreServicesRepository storeServicesRepo;

    @Override
    public BusinessHAL insertBusiness(Business business) {
        repo.save(business);
        return null;
    }

    @Override
    public BusinessHAL insertStore(Store store) {
        storeRepo.save(store);
        return null;
    }

    @Override
    public void insertServiceForStore(StoreServices s) {
        storeServicesRepo.save(s);
    }
}
