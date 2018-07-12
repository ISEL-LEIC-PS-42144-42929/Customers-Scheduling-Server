package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.ClientStoreInputModel;
import com.customersscheduling.Controller.InputModels.OwnerInputModel;
import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Controller.InputModels.StoreInputModel;
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
public class StoreControllerTest {

    @Autowired
    private MockMvc mvc;

    ObjectMapper om = new ObjectMapper();

    private final String [] authHeader = {"Authorization", "Bearer unittest"};

    private final String POST_STORE_URI = "/store/owner/%s";
    private final String GET_STORE_URI = "/store/%s";
    private final String GET_STORE_BY_NAME_URI = "/store";
    private final String DELETE_STORE_URI = "/store/%s";
    private final String POST_CLIENT_STORE_URI = "/store/%s/client/%s";
    private final String GET_CLIENTS_URI = "/store/%s/clients";
    private final String GET_PENDENTREQS_URI = "/store/%s/pendentrequests";
    private final String RESPONSE_CONTENT_TYPE="application/hal+json;charset=UTF-8";
    private final String RESPONSE_PROBLEM_CONTENT_TYPE="application/problem+json";

    private OwnerControllerTest ownerTests;
    private ClientControllerTest clientTests;

    public StoreControllerTest(){}


    public static StoreControllerTest getInstance(MockMvc mvc){
        StoreControllerTest s = new StoreControllerTest();
        s.setMvc(mvc);
        s.init();
        return s;
    }

    @Before
    public void init(){
        ownerTests = OwnerControllerTest.getInstance(mvc);
        clientTests = ClientControllerTest.getInstance(mvc);
    }


    @Test
    public void getClientsOfStoreTest() throws Exception {
        clientTests.insertClientTest();
        StoreInputModel s = getStoreInputModel();
        String URI = String.format(GET_CLIENTS_URI, s.nif);
        mvc.perform(get(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$._embedded.clientResourceList").isArray());
    }

    @Test
    public void getStoresByNameTest() throws Exception {
        insertStore();
        StoreInputModel c = getStoreInputModel();
        mvc.perform(get(GET_STORE_BY_NAME_URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE))
                    .param("name", c.name))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$._embedded.storeResourceList").isArray());

    }

    @Test
    public void getStoresByCategoryAndLocationTest() throws Exception {
        insertStore();
        StoreInputModel c = getStoreInputModel();
        mvc.perform(get(GET_STORE_BY_NAME_URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE))
                    .param("location", c.city)
                    .param("category",c.category))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$._embedded.storeResourceList").isArray());
    }

    @Test
    public void getStoreTest() throws Exception {
        insertStore();
        StoreInputModel c = getStoreInputModel();
        String URI = String.format(GET_STORE_URI, c.nif);
        mvc.perform(get(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$.store.storeName").value(c.name));

    }

    @Test
    public void insertStore() throws Exception {
        ownerTests.insertOwnerTest();
        OwnerInputModel owner = ownerTests.getOwnerInputModel();
        StoreInputModel store = getStoreInputModel();
        String body = om.writeValueAsString(store);
        String URI = String.format(POST_STORE_URI, owner.email);
        mvc.perform(post(URI)
                    .header(authHeader[0], authHeader[1])
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$.store.storeName").value(store.name));
    }

    @Test
    public void setClientForStoreTest() throws Exception {
        clientTests.insertClientTest();
        PersonInputModel client = clientTests.getClientInputModel();
        StoreInputModel store = getStoreInputModel();
        ClientStoreInputModel input = getClientStoreInputModel();
        String body = om.writeValueAsString(input);
        String URI = String.format(POST_CLIENT_STORE_URI, store.nif, client.email);
        mvc.perform(post(URI)
                    .header(authHeader[0], authHeader[1])
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$.store.storeName").value(store.name));
    }


    @Test
    public void getPendentRequestsOfStoreTest() throws Exception {
        StoreInputModel s = getStoreInputModel();
        String URI = String.format(GET_PENDENTREQS_URI, s.nif);
        mvc.perform(get(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$._embedded.clientResourceList").isArray());
    }

    @Test
    public void deleteStore() throws Exception {
        insertStore();
        StoreInputModel c = getStoreInputModel();
        String URI = String.format(DELETE_STORE_URI, c.nif);
        mvc.perform(delete(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE));
    }

    public StoreInputModel getStoreInputModel(){
        StoreInputModel store = new StoreInputModel();
        store.nif="123456789";
        store.zip_code="23456";
        store.lot="9";
        store.street="Wall Street";
        store.city="New York";
        store.name="TestStore";
        store.contact="contact";
        store.category="Test Category";
        store.country="USA";
        return store;
    }

    public ClientStoreInputModel getClientStoreInputModel() {
        ClientStoreInputModel c = new ClientStoreInputModel();
        c.score=3;
        c.accepted=true;
        return c;
    }

    public void setMvc(MockMvc mvc) {
        this.mvc = mvc;
    }
}