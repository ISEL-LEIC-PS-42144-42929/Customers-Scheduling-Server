package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.ServiceInputModel;
import com.customersscheduling.Controller.InputModels.StoreInputModel;
import com.customersscheduling.Domain.Service;
import com.customersscheduling.Repository.ServiceRepository;
import com.customersscheduling.Service.ServicesOfStoreService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StoreServicesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ServiceRepository servRepo;

    ObjectMapper om = new ObjectMapper();

    private final String [] authHeader = {"Authorization", "Bearer unittest"};
    public static final String POST_SERVICE_URI = "/store/%s/services";
    private final String GET_SERVICES_OF_STORE_URI = "/store/%s/services";
    private final String GET_SERVICE_URI = "/store/%s/services/%s";
    private final String GET_STAFF_OF_SERVICE_URI = "/store/%s/services/%s/staff";
    private final String GET_DISP_OF_SERVICE_URI = "/store/%s/services/%s/disp";
    private final String DELETE_SERVICE_URI = "/store/%s/services/%s";
    private final String RESPONSE_CONTENT_TYPE="application/hal+json;charset=UTF-8";
    private final String RESPONSE_PROBLEM_CONTENT_TYPE="application/problem+json";

    private StoreControllerTest storeTests;

    public static StoreServicesControllerTest getInstance(MockMvc mvc) {
        StoreServicesControllerTest s = new StoreServicesControllerTest();
        s.setMvc(mvc);
        s.init();
        return s;
    }

    @Before
    public void init(){
        storeTests = StoreControllerTest.getInstance(mvc);
    }

    @Test
    public void insertServiceForStoreTest() throws Exception {
        storeTests.insertStore();
        StoreInputModel store = storeTests.getStoreInputModel();
        ServiceInputModel s = getServiceInputModel();
        String body = om.writeValueAsString(s);
        String URI = String.format(POST_SERVICE_URI, store.nif);
        mvc.perform(post(URI)
                    .header(authHeader[0], authHeader[1])
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$.service.title").value(s.title));
    }

    @Test
    public void getServicesOfStoreTest() throws Exception {
        StoreInputModel store = storeTests.getStoreInputModel();
        String URI = String.format(GET_SERVICES_OF_STORE_URI, store.nif);
        mvc.perform(get(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$._embedded.serviceResourceList").isArray());
    }

    @Test
    public void getService() throws Exception {
        insertServiceForStoreTest();
        StoreInputModel store = storeTests.getStoreInputModel();
        ServiceInputModel s = getServiceInputModel();
        int service_id = servRepo.findByTitle(s.title).getId();
        String URI = String.format(GET_SERVICE_URI, store.nif, service_id);
        mvc.perform(get(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$.service.title").value(getServiceInputModel().title));
    }

    @Test
    public void getStaffOfServiceTest() throws Exception {
        insertServiceForStoreTest();
        ServiceInputModel s = getServiceInputModel();
        StoreInputModel store = storeTests.getStoreInputModel();
        int service_id = servRepo.findByTitle(s.title).getId();
        String URI = String.format(GET_STAFF_OF_SERVICE_URI, store.nif, service_id);
        mvc.perform(get(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$._embedded.staffResourceList").isArray());
    }

    @Test
    public void deleteService() throws Exception {
        insertServiceForStoreTest();
        StoreInputModel store = storeTests.getStoreInputModel();
        ServiceInputModel s = getServiceInputModel();
        int service_id = servRepo.findByTitle(s.title).getId();
        String URI = String.format(DELETE_SERVICE_URI, store.nif, service_id);
        mvc.perform(delete(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE));
    }

    public ServiceInputModel getServiceInputModel(){
        ServiceInputModel s = new ServiceInputModel();
        s.title="Tst Service";
        s.price=7.5;
        s.duration=30;
        s.description="Tst service description";
        return s;
    }

    public void setMvc(MockMvc mvc) {
        this.mvc = mvc;
    }
}