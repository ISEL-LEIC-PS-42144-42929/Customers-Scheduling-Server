package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Client;
import com.customersscheduling.DTO.ClientStores;
import com.customersscheduling.DTO.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientStoresRepository extends JpaRepository<ClientStores, Integer> {

    @Override
    ClientStores save(ClientStores entity);

    @Query("select s.pk.client.email from ClientStores s where s.pk.store.nif = :nif and s.accepted = 0")
    List<String> findPendentRequestsOfStore(@Param("nif") String nif);
}