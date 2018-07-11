package com.customersscheduling.Repository;

import com.customersscheduling.Domain.ClientStores;
import com.customersscheduling.Domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientStoresRepository extends JpaRepository<ClientStores, Integer> {

    @Override
    ClientStores save(ClientStores entity);

    @Query("select s.pk.client.email from ClientStores s where s.pk.store.nif = :nif and s.accepted = 0")
    List<String> findPendentRequestsOfStore(@Param("nif") String nif);

    List<ClientStores> findByPk_Store_NifAndAccepted(String nif, boolean a);

    List<ClientStores> findByPk_Client_EmailAndAccepted(String email, boolean a);

    void deleteByPk_Store_Nif(String nif);

    void deleteByPk_Client_Email(String email);

    void deleteByPk_Client_EmailAndPk_Store_Nif(String email, String nif);

    List<ClientStores> findByPk_Store_Nif(String nif);
}
