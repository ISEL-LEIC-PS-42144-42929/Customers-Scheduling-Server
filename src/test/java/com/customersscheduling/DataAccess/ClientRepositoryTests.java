package com.customersscheduling.DataAccess;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.Domain.Person;
import com.customersscheduling.MvcConfig;
import com.customersscheduling.Repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
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
