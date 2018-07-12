package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Owner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
public class OwnerRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository clientRepo;

    @Autowired
    private OwnerRepository ownerRepo;

    ClientRepositoryTests clientRepoTests = new ClientRepositoryTests();

    @Test
    public void testFind() {
        clientRepo.save(clientRepoTests.getClient());
        entityManager.persist(getOwner());
        Optional<Owner> owner = ownerRepo.findByClient_Email(getOwner().getClient().getEmail());
        assertEquals("Foo", owner.get().getClient().getEmail());
    }

    private Owner getOwner(){
        Owner o = new Owner();
        o.setClient(clientRepoTests.getClient());
        o.setNif("111111111");
        return o;
    }
}
