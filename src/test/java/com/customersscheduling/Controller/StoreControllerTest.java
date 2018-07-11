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
    private final String POST_CLIENT_STORE_URI = "/store/owner/%s";
    private final String RESPONSE_CONTENT_TYPE="application/hal+json;charset=UTF-8";
    private final String RESPONSE_PROBLEM_CONTENT_TYPE="application/problem+json";

    private OwnerControllerTest ownerTests;
    private ClientControllerTest clientTests;

    public StoreControllerTest(MockMvc mvc) {
        this.mvc = mvc;
        init();
    }

    @Before
    public void init(){
        ownerTests = new OwnerControllerTest(mvc);
        clientTests = new ClientControllerTest(mvc);
    }


    @Test
    public void getClientsOfStore() {
    }

    @Test
    public void getStoresByName() {
    }

    @Test
    public void getStoresByCategoryAndLocation() {
    }

    @Test
    public void getStore() {
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
    public void updateStoreAddress() {
    }

    @Test
    public void updateInfoStore() {
    }

    @Test
    public void setClientForStoreTest() throws Exception {
        clientTests.insertClientTest();
        PersonInputModel client = clientTests.getClientInputModel();

    }

    @Test
    public void getPendentRequestsOfStore() {
    }

    @Test
    public void deleteStore() {
    }

    private StoreInputModel getStoreInputModel(){
        StoreInputModel store = new StoreInputModel();
        store.nif="123456789";
        store.zip_code="23456";
        store.lot="9";
        store.street="Wall Street";
        store.city="New York";
        store.name="Test Store";
        store.contact="contact";
        store.category="Test Category";
        store.country="USA";
        return store;
    }
}