package com.customersscheduling.DataAccess;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.Domain.Person;
import com.customersscheduling.Repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository repo;

    @Test
    public void testFindByName() {
        entityManager.persist(getClient());
        Optional<Person> client = repo.findByEmail("Foo");
        assertEquals("Foo", client.get().getEmail());
    }

    private Client getClient(){
        Client c = new Client();
        c.setEmail("Foo");
        c.setName("FooName");
        c.setGender(1);
        c.setContact("FooContact");
        return c;
    }
}
