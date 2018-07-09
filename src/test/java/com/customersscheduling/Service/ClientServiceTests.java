package com.customersscheduling.Service;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.ExceptionHandling.CustomExceptions.InvalidBodyException;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ValueAlreadyExistsException;
import com.customersscheduling.Repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTests {

    @Autowired
    private ClientService service;

    @MockBean
    private PersonRepository repoMock;

    @Test
    public void createClientSuccessfully(){
        when(repoMock.findByEmail(eq("Foo"))).thenReturn(null);
        doAnswer(returnsFirstArg()).when(repoMock).save(any(Client.class));
        Client client = service.insertClient(getClient());
        assertEquals("FooName", client.getName());
        assertNotNull(client.getEmail());
    }

    @Test(expected = InvalidBodyException.class)
    public void createClientWithEmptyEmail() throws Exception {
        Client c = getClient();
        c.setEmail(null);
        service.insertClient(c);
    }

    @Test(expected = ValueAlreadyExistsException.class)
    public void createClientWithExistingEmail() throws Exception {
        doThrow(new ValueAlreadyExistsException()).when(repoMock).findByEmail(eq("Foo"));
        service.insertClient(getClient());
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
