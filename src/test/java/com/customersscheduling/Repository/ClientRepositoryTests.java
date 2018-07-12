package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.Domain.Person;
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
public class ClientRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository repo;

    @Test
    public void testFindByEmail() {
        entityManager.persist(getClient());
        Optional<Person> client = repo.findByEmail("Foo");
        assertEquals("Foo", client.get().getEmail());
    }

    @Test
    public void testSave() {
        Person client = repo.save(getClient());
        assertEquals(client.getEmail(), getClient().getEmail());
    }

    public Client getClient(){
        Client c = new Client();
        c.setEmail("Foo");
        c.setName("FooName");
        c.setGender(1);
        c.setContact("FooContact");
        return c;
    }
}
