package com.customersscheduling.Service;

import com.customersscheduling.DTO.Business;
import com.customersscheduling.DTO.Store;
import com.customersscheduling.HALObjects.BusinessHAL;

public interface IBusinessService {
    BusinessHAL insertBusiness(Business business);
    BusinessHAL insertStore(Store store);
}
