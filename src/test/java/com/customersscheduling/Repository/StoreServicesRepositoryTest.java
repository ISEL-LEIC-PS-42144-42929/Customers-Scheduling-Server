package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Service;
import com.customersscheduling.Domain.Store;
import com.customersscheduling.Domain.StoreServices;
import com.customersscheduling.Domain.StoreServicesPK;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
public class StoreServicesRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StoreServicesRepository storeServiceRepo;

    @Autowired
    private ServiceRepository serviceRepo;

    @Autowired
    private StoreRepository storeRepo;

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private OwnerRepository ownerRepo;

    ServiceRepositoryTest servRepoTest = new ServiceRepositoryTest();
    StoreRepositoryTest storeRepoTest = new StoreRepositoryTest();
    OwnerRepositoryTests owner = new OwnerRepositoryTests();
    ClientRepositoryTests client = new ClientRepositoryTests();

    @Test
    public void save() {
        StoreServices storeServices = insertStoreServs();
        assertEquals(storeServices.getPk().getService().getDescription(), servRepoTest.getService().getDescription());
    }

    @Test
    public void findByPk_Store() {

    }

    @Test
    public void findByPk_Store_Nif() {
    }

    @Test
    public void findByPk_Service_Id() {
    }

    @Test
    public void deleteByPk_Store_Nif() {
    }

    private StoreServices insertStoreServs(){
        personRepo.save(client.getClient());
        ownerRepo.save(owner.getOwner());
        Store store = storeRepoTest.getStore();
        Store save = storeRepo.save(store);
        Service save1 = serviceRepo.save(servRepoTest.getService());
        StoreServices ss = new StoreServices();
        StoreServicesPK sspk = new StoreServicesPK();
        sspk.setStore(save);
        sspk.setService(save1);
        ss.setPk(sspk);
        return storeServiceRepo.save(ss);
    }
}