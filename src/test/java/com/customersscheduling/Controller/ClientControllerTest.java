package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.PersonInputModel;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.Service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.xml.ws.Response;

import java.util.function.Consumer;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    ObjectMapper om = new ObjectMapper();

    private final String [] authHeader = {"Authorization", "Bearer unittest"};
    private final String POST_CLIENT_URI = "/person/client";
    private final String RESPONSE_CONTENT_TYPE="application/hal+json";

    @Test
    public void insertClientTest() throws Exception{
        PersonInputModel c = getClientInputModel();
        String body = om.writeValueAsString(c);
        mvc.perform(post(POST_CLIENT_URI)
                        .header(authHeader[0], authHeader[1])
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(RESPONSE_CONTENT_TYPE));
    }

    @Test
    public void getClient() throws Exception{
        mvc.perform(get("/person/tstclientemail/client")
                        .accept(MediaType.parseMediaType("application/hal+json;charset=UTF-8")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json;charset=UTF-8"))
                .andExpect(jsonPath("$.person.name").value("tstclientname"));
    }

    @Test
    public void getPendentRequestOfClient() {
    }

    @Test
    public void getBooksOfClient() {
    }

    @Test
    public void getStoresOfClient() {
    }

    private PersonInputModel getClientInputModel(){
        PersonInputModel person = new PersonInputModel();
        person.email="tstclient";
        person.contact="tstclientemail";
        person.gender=1;
        person.name="tstclientname";
        return person;
    }
}