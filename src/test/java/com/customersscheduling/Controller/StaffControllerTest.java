package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Controller.InputModels.StaffInputModel;
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
public class StaffControllerTest {

    @Autowired
    private MockMvc mvc;

    ObjectMapper om = new ObjectMapper();

    private final String [] authHeader = {"Authorization", "Bearer unittest"};
    private final String POST_STAFF_URI = "/person/staff";
    private final String GET_STAFF_URI = "/person/staff/%s";
    private final String UPDATE_STAFF_URI = "/person/staff/%s";
    private final String DELETE_STAFF_URI = "/person/staff/%s";
    private final String RESPONSE_CONTENT_TYPE="application/hal+json;charset=UTF-8";
    private final String RESPONSE_PROBLEM_CONTENT_TYPE="application/problem+json";

    private StoreControllerTest storeTests;

    @Before
    public void init(){
        storeTests = StoreControllerTest.getInstance(mvc);
    }

    @Test
    public void insertStaffTest() throws Exception {
        storeTests.insertStore();
        StaffInputModel c = getStaffInputModel();
        String body = om.writeValueAsString(c);
        mvc.perform(post(POST_STAFF_URI)
                    .header(authHeader[0], authHeader[1])
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$.person.name").value(c.name));
    }



    @Test
    public void getStaffTest() throws Exception {
        insertStaffTest();
        StaffInputModel c = getStaffInputModel();
        String URI = String.format(GET_STAFF_URI, c.email);
        mvc.perform(get(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$.person.name").value(c.name));
        deleteStaffTest();
    }

    @Test
    public void deleteStaffTest() throws Exception {
        insertStaffTest();
        StaffInputModel c = getStaffInputModel();
        String URI = String.format(DELETE_STAFF_URI, c.email);
        mvc.perform(delete(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE));
    }

    private StaffInputModel getStaffInputModel() {
        StaffInputModel s = new StaffInputModel();
        s.email="tststaffemail";
        s.name="tststaffname";
        s.nif="123456789";
        return s;
    }
}