package com.customersscheduling.Service;


import com.customersscheduling.Domain.Category;
import com.customersscheduling.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilService implements IUtilService{

    @Autowired
    ServiceRepository serviceRepo;

    @Override
    public void insertService(com.customersscheduling.Domain.Service service) {

        serviceRepo.save(service);
    }

    @Override
    public void insertCategory(Category category) {

    }
}
