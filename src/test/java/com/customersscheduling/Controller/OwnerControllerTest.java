package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.OwnerInputModel;
import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Domain.Owner;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.customersscheduling.Controller.ClientControllerTest.POST_CLIENT_URI;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest {

    @Autowired
    private MockMvc mvc;

    ObjectMapper om = new ObjectMapper();

    private final String [] authHeader = {"Authorization", "Bearer unittest"};
    private final String POST_OWNER_URI = "/person/owner";
    private final String GET_OWNER_URI = "/person/owner/%s";
    private final String DELETE_OWNER_URI = "/person/owner/%s";
    private final String RESPONSE_CONTENT_TYPE="application/hal+json;charset=UTF-8";

    private ClientControllerTest clientTests;

    public OwnerControllerTest(MockMvc mvc) {
        this.mvc = mvc;
        init();
    }

    @Before
    public void init(){
        clientTests = new ClientControllerTest(mvc);
    }

    @Test
    public void insertOwnerTest() throws Exception{
        String clientBody = om.writeValueAsString(clientTests.getClientInputModel());
        mvc.perform(post(POST_CLIENT_URI)
                .header(authHeader[0], authHeader[1])
                .content(clientBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        OwnerInputModel o = getOwnerInputModel();
        String body = om.writeValueAsString(o);
        mvc.perform(post(POST_OWNER_URI)
                    .header(authHeader[0], authHeader[1])
                    .content(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$.person.nif").value(o.nif));
    }

    @Test
    public void getOwnerTest() throws Exception {
        insertOwnerTest();
        OwnerInputModel c = getOwnerInputModel();
        String URI = String.format(GET_OWNER_URI, c.email);
        mvc.perform(get(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$.person.nif").value(c.nif));
    }

    @Test
    public void deleteOwnerTest() throws Exception {
        insertOwnerTest();
        OwnerInputModel c = getOwnerInputModel();
        String URI = String.format(DELETE_OWNER_URI, c.email);
        mvc.perform(delete(URI)
                .header(authHeader[0], authHeader[1])
                .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE));
    }

    public OwnerInputModel getOwnerInputModel(){
        OwnerInputModel c = new OwnerInputModel();
        c.email="tstclientemail";
        c.nif="123456789";
        return c;
    }
}