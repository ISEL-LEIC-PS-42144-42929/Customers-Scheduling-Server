package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, String> {

    void deleteByStore_Nif(String nif);

}
