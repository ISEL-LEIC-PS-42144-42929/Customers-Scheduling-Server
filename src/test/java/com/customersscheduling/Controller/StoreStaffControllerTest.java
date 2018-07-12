package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.ServiceInputModel;
import com.customersscheduling.Controller.InputModels.StaffInputModel;
import com.customersscheduling.Controller.InputModels.StoreInputModel;
import com.customersscheduling.Repository.ServiceRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StoreStaffControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ServiceRepository servRepo;

    ObjectMapper om = new ObjectMapper();

    private final String [] authHeader = {"Authorization", "Bearer unittest"};
    public static final String POST_STAFF_FOR_SERVICE_URI = "/store/%s/services/%s/staff/%s";
    private final String GET_STAFF_OF_STORE_URI = "/store/%s/staff";
    private final String RESPONSE_CONTENT_TYPE="application/hal+json;charset=UTF-8";

    private StoreServicesControllerTest storeServsTests;
    private StoreControllerTest storeTests;
    private StaffControllerTest staffTests;

    @Before
    public void init(){
        storeServsTests = StoreServicesControllerTest.getInstance(mvc);
        staffTests = StaffControllerTest.getInstance(mvc);
        storeTests = StoreControllerTest.getInstance(mvc);
    }

    @Test
    public void insertStaffForServiceTest() throws Exception {
        staffTests.insertStaffTest();
        storeServsTests.insertServiceForStoreTest();
        StoreInputModel store = storeTests.getStoreInputModel();
        ServiceInputModel service = storeServsTests.getServiceInputModel();
        StaffInputModel staff = staffTests.getStaffInputModel();
        int service_id = servRepo.findByTitle(service.title).getId();

        String URI = String.format(POST_STAFF_FOR_SERVICE_URI, store.nif, service_id, staff.email);
        mvc.perform(post(URI)
                    .header(authHeader[0], authHeader[1])
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$.service.title").value(service.title));

    }

    @Test
    public void getStaffOfStoreTest() throws Exception {
        insertStaffForServiceTest();
        StoreInputModel store = storeTests.getStoreInputModel();
        String URI = String.format(GET_STAFF_OF_STORE_URI, store.nif);
        mvc.perform(get(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$._embedded.staffResourceList").isArray());
    }
}