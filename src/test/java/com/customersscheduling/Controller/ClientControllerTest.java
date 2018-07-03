package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.PersonInputModel;

import com.customersscheduling.Domain.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.xml.ws.Response;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void insertClient() throws Exception{
        PersonInputModel c = new PersonInputModel();
        c.email="tstclientemail"; c.name="tstclientname";
        String body = om.writeValueAsString(c);
        MvcResult result = mvc.perform(post("/person/client").content(body)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        //Response response = om.readValue(resultContent, Response.class);
        //Assert.assertTrue(response.isDone() == Boolean.TRUE);
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
        person.gender=true;
        person.name="tstclientname";
        return person;
    }
}