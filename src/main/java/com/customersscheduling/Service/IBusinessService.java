package com.customersscheduling.Service;

import com.customersscheduling.DTO.BusinessDto;
import com.customersscheduling.HALObjects.BusinessHAL;

public interface IBusinessService {
    BusinessHAL insertBusiness(BusinessDto business);
}
