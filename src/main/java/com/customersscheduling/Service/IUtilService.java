package com.customersscheduling.Service;

import com.customersscheduling.DTO.Category;
import com.customersscheduling.DTO.Service;

public interface IUtilService {

    void insertService(Service service);
    void insertCategory(Category category);
}
