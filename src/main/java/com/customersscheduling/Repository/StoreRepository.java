package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import static org.hibernate.hql.internal.antlr.HqlTokenTypes.FROM;
import static org.hibernate.loader.Loader.SELECT;

public interface StoreRepository extends JpaRepository<Store, String> {

    @Override
    Store save(Store entity);

    Store findByNif(String nif);

    List<Store> findByOwner_Client_Email(String email);

    @Query("SELECT s FROM Store s WHERE s.storeName LIKE :name")
    List<Store> findByName(@Param("name") String name);

    List<Store> findByAddress_CityAndCategory_Name(String city, String name);

    void deleteByOwner_Client_Email(String email);
}