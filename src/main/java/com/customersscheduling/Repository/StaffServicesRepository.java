package com.customersscheduling.Repository;

import com.customersscheduling.Domain.StaffServices;
import com.customersscheduling.Domain.StaffServicesPK;
import com.customersscheduling.Domain.StoreServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StaffServicesRepository extends JpaRepository<StaffServices, StaffServicesPK> {

    @Override
    StaffServices save(StaffServices entity);

    List<StaffServices> getByPk_StoresServices_Pk_Service_IdAndPk_StoresServices_Pk_Store_Nif(int id, String nif);

    List<StaffServices> getByPk_StoresServices_Pk_Store_Nif(String nif);

    Optional<StaffServices> getByPk_StoresServices_Pk_Service_IdAndPk_StoresServices_Pk_Store_NifAndPk_Staff_Email(int id, String nif, String email);

    List<StaffServices> getByPk_StoresServices(StoreServices ss);

    void deleteByPk_StoresServices_Pk_Store_Nif(String nif);
}