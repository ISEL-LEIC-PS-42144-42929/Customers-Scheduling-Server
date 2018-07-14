package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, String> {

    void deleteByStore_Nif(String nif);

    List<Staff> findByStore_Nif(String nif);
}
