package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
public class ServiceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ServiceRepository serviceRepo;



    @Test
    public void save() {
        Service save = serviceRepo.save(getService());
        assertEquals(save.getDescription(), getService().getDescription());
    }

    @Test
    public void findById() {
        Service serv = entityManager.persist(getService());
        Optional<Service> serv2 = serviceRepo.findById(serv.getId());
        assertEquals(serv.getId(), serv2.get().getId());
    }

    @Test
    public void findByTitle() {
        Service serv = entityManager.persist(getService());
        Service serv2 = serviceRepo.findByTitle(serv.getTitle());
        assertEquals(serv.getId(), serv2.getId());
    }

    public Service getService(){
        return new Service("service description", "title", 7.5, 30);
    }
}