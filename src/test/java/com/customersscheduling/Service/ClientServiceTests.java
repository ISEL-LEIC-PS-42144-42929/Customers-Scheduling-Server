package com.customersscheduling.Service;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.Repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTests {

    @Autowired
    private ClientService service;

    @MockBean
    private PersonRepository repo;

    @Test
    public void createClientSuccessfully(){
        when(repo.findByEmail(eq("Foo"))).thenReturn(null);
        doAnswer(returnsFirstArg()).when(repo).save(any(Client.class));
        Client client = service.insertClient(getClient());
        assertEquals("FooName", client.getName());
        assertNotNull(client.getEmail());
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
