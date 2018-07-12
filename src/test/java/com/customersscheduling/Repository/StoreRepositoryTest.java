package com.customersscheduling.Repository;

import com.customersscheduling.Domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
public class StoreRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StoreRepository storeRepo;

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private OwnerRepository ownerRepo;

    OwnerRepositoryTests owner = new OwnerRepositoryTests();
    ClientRepositoryTests client = new ClientRepositoryTests();

    @Test
    public void save() {
        personRepo.save(client.getClient());
        ownerRepo.save(owner.getOwner());
        Store store = getStore();
        Store save = storeRepo.save(store);
        assertEquals(save.getNif(), store.getNif());
    }

    @Test
    public void findByNif() {
        personRepo.save(client.getClient());
        Owner owner = ownerRepo.save(this.owner.getOwner());
        Store store = getStore();
        store.setOwner(owner);
        entityManager.persist(store);
        store = storeRepo.findByNif(getStore().getNif());
        assertEquals(store.getNif(), getStore().getNif());
    }

    public Store getStore(){
        Store s = new Store();
        s.setNif("909090909");
        s.setContact("969594932");
        s.setStoreName("Tst Store Name");
        s.setAddress(new Address("1234-123","street", "lot", "Lisbon", "Portugal"));
        s.setOwner(owner.getOwner());
        s.setCategory(new Category("tst cat", "tst cat"));
        return s;
    }

    public Store getStore(Owner o){
        Store s = new Store();
        s.setNif("909090909");
        s.setContact("969594932");
        s.setStoreName("Tst Store Name");
        s.setAddress(new Address("1234-123","street", "lot", "Lisbon", "Portugal"));
        s.setOwner(o);
        s.setCategory(new Category("tst cat", "tst cat"));
        return s;
    }
}