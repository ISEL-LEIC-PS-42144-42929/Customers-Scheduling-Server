package com.customersscheduling.Service;

import com.customersscheduling.Domain.Category;
import com.customersscheduling.Domain.Service;

public interface IUtilService {

    void insertService(Service service);
    void insertCategory(Category category);
}
