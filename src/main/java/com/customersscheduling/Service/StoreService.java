package com.customersscheduling.Service;

import com.customersscheduling.DAO.IStoreDao;
import org.springframework.stereotype.Service;

@Service
public class StoreService implements IStoreService {

    private final IStoreDao storeDao;

    public StoreService(IStoreDao storeDao) {
        this.storeDao = storeDao;
    }
}
