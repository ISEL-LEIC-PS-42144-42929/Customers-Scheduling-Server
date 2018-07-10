package com.customersscheduling;

import com.customersscheduling.Controller.ClientDTOs.ServicesOfStore;
import com.customersscheduling.Controller.InputModels.*;
import com.customersscheduling.Domain.Timetable;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseFillingTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void addPersons() throws Exception{
        PersonInputModel c = new PersonInputModel();
        //10 Clients
        for (int i = 0; i < 10; i++) {
            c.email="tstclientemail"+i; c.name="tstclientname"+i;
            req("/person/client", c);
        }
        //1 Owner
        OwnerInputModel o= new OwnerInputModel();
        o.email="tstowneremail"; o.nif="123456789";
        req("/person/owner", o);
        //10 Staff
        for (int i = 0; i < 10; i++) {
            c.email="tststaffemail"+i; c.name="tststaffname"+i;
            req("/person/staff", c);
        }
    }

    @Test
    public void addTimetableForStaffs() throws Exception{
        TimetableInputModel tt = new TimetableInputModel();
        tt.open_hour = 8.0; tt.init_break=13.0;
        tt.finish_break=15.0; tt.close_hour=19.0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                if(j!=3){
                    tt.week_day=j;
                    req("/timetable/staff/tststaffemail"+i, tt);
                }else{
                    req("/timetable/staff/tststaffemail"+i, new TimetableInputModel());
                }
            }
        }
    }

    @Test
    public void addStore() throws Exception{
        StoreInputModel sim = new StoreInputModel();
        sim.category = "tstcat"; sim.city="lisbon"; sim.contact="tststorecontact";
        sim.name="tststorename"; sim.street="wall street"; sim.nif="123456789";
        sim.lot="9"; sim.zip_code="1234-123"; sim.country="usa";
        req("/store/tstowneremail", sim);
        TimetableInputModel tt = new TimetableInputModel();
        tt.open_hour = 8.0; tt.init_break=13.0;
        tt.finish_break=15.0; tt.close_hour=19.0;
        for (int j = 0; j < 7; j++) {
            if(j!=3){
                tt.week_day=j;
                req("/timetable/store/123456789", tt);
            }else{
                TimetableInputModel tt2 = new TimetableInputModel(); tt2.week_day=j;
                req("/timetable/store/123456789", tt2);
            }
        }
    }

    @Test
    public void addServices() throws Exception{
        ServiceInputModel sim = new ServiceInputModel();
        for (int i = 0; i < 10; i++) {
            sim.description="tstdescr"+i;
            sim.duration=30;
            sim.price=7.5;
            sim.title="tstservicetitle "+1;
            req("/store/123456789/service", sim);
        }
    }

    @Test
    public void addStaffForServices() throws Exception {
        List<Integer> servicesIds = getServicesIds();
        servicesIds.forEach(id -> {
            String email = "tststaffemail9";
            try {
                req("/store/123456789/service/" + id + "/staff/" + email, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void addScoresToStore() throws Exception {
        for (int i = 0; i < 10; i++) {
            String email="tstclientemail"+i;
            //req("/person/client", c);
        }
    }


    private void req(String uri, Object o) throws Exception{
        String body = om.writeValueAsString(o);
        mvc.perform(post(uri).content(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private List<Integer> getServicesIds() throws Exception{
        MvcResult mvcResult = mvc.perform(get("/store/123456789/services"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ServicesOfStore servicesOfStore = om.readValue(content, ServicesOfStore.class);
        return Arrays.stream(servicesOfStore._embedded.serviceResourceList)
                        .map(i -> i.service.id)
                        .collect(Collectors.toList());
    }
}
