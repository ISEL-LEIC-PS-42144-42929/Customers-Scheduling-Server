package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static org.hibernate.hql.internal.antlr.HqlTokenTypes.FROM;
import static org.hibernate.loader.Loader.SELECT;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Override
    Store save(Store entity);

    Store findByNif(String nif);

    List<Store> findByOwnerEmail(String email);

    @Query("SELECT s FROM Store s WHERE CONTAINS (s.storeName, :name")
    List<Store> findByName(@Param("name") String name);
}