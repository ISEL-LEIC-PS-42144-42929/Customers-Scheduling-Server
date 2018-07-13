package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.PersonInputModel;

import com.customersscheduling.Domain.Client;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.org.apache.xalan.internal.xsltc.dom.LoadDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/snippets")
public class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    ObjectMapper om = new ObjectMapper();

    private final String [] authHeader = {"Authorization", "Bearer unittest"};
    public static final String POST_CLIENT_URI = "/person/client";
    private final String GET_CLIENT_URI = "/person/client/%s";
    private final String GET_PENDENTREQS_URI = "/person/client/%s/pendentrequests";
    private final String GET_BOOKS_URI = "/person/client/%s/books";
    private final String GET_STORES_URI = "/person/client/%s/stores";
    private final String DELETE_CLIENT_URI = "/person/client/%s";
    private final String RESPONSE_CONTENT_TYPE="application/hal+json;charset=UTF-8";
    private final String RESPONSE_PROBLEM_CONTENT_TYPE="application/problem+json";

    public ClientControllerTest() {

    }

    public static ClientControllerTest getInstance(MockMvc mvc){
        ClientControllerTest c = new ClientControllerTest();
        c.setMvc(mvc);
        return c;
    }

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
                .andExpect(content().contentTypeCompatibleWith(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$.person.name").value(c.name))
                .andDo(document("insert-client-example", responseFields(
                        fieldWithPath("person").description("The user info."),
                            fieldWithPath("person.email").description("User's email."),
                            fieldWithPath("person.name").description("User's name."),
                            fieldWithPath("person.contact").description("User's contact."),
                            fieldWithPath("person.gender").description("User's gender."),
                        fieldWithPath("accepted").description("Used in store context to tell either or not the client is registered to that store."),
                        fieldWithPath("_links").description("Hypermedia navigation and some actions hyperlinks."),
                            fieldWithPath("_links.get.href").description("Hyperlink to get client info."),
                            fieldWithPath("_links.insert.href").description("Hyperlink to insert client info."),
                            fieldWithPath("_links.pendent_requests.href").description("Hyperlink to get client info."),
                            fieldWithPath("_links.stores.href").description("Hyperlink to get client info."),
                            fieldWithPath("_links.set_store.href").description("Hyperlink to get client info."),
                                fieldWithPath("_links.set_store.templated").description("Hyperlink to get client info."),
                            fieldWithPath("_links.score.href").description("Hyperlink to get client info."),
                                fieldWithPath("_links.score.templated").description("Hyperlink to get client info."),
                            fieldWithPath("_links.accept.href").description("Hyperlink to get client info."),
                                fieldWithPath("_links.accept.templated").description("Hyperlink to get client info."),
                            fieldWithPath("_links.decline_client.href").description("Hyperlink to get client info."),
                                fieldWithPath("_links.decline_client.templated").description("Hyperlink to get client info."),
                            fieldWithPath("_links.self.href").description("Hyperlink to get client info.")
                )));
    }

    @Test
    public void getClientTest() throws Exception{
        insertClientTest();
        PersonInputModel c = getClientInputModel();
        String URI = String.format(GET_CLIENT_URI, c.email);
        mvc.perform(get(URI)
                        .header(authHeader[0], authHeader[1])
                        .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE))
                .andExpect(jsonPath("$.person.name").value(c.name));
        deleteClientTest();
    }

    @Test
    public void getPendentRequestOfClientTest() throws Exception {
        PersonInputModel c = getClientInputModel();
        String URI = String.format(GET_PENDENTREQS_URI, c.email);
        mvc.perform(get(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE));
    }

    @Test
    public void getBooksOfClientTest() throws Exception {
        PersonInputModel c = getClientInputModel();
        String URI = String.format(GET_BOOKS_URI, c.email);
        mvc.perform(get(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE));
    }

    @Test
    public void getStoresOfClientTest() throws Exception {
        PersonInputModel c = getClientInputModel();
        String URI = String.format(GET_STORES_URI, c.email);
        mvc.perform(get(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE));
    }

    @Test
    public void deleteClientTest() throws Exception {
        insertClientTest();
        PersonInputModel c = getClientInputModel();
        String URI = String.format(DELETE_CLIENT_URI, c.email);
        mvc.perform(delete(URI)
                    .header(authHeader[0], authHeader[1])
                    .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(RESPONSE_CONTENT_TYPE));
    }

    @Test
    public void deleteClientWithErrorTest() throws Exception {
        deleteClientTest();
        PersonInputModel c = getClientInputModel();
        String URI = String.format(DELETE_CLIENT_URI, c.email);
        mvc.perform(delete(URI)
                .header(authHeader[0], authHeader[1])
                .accept(MediaType.parseMediaType(RESPONSE_CONTENT_TYPE)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(RESPONSE_PROBLEM_CONTENT_TYPE));
    }

    public PersonInputModel getClientInputModel(){
        PersonInputModel person = new PersonInputModel();
        person.email="tstclient";
        person.contact="tstclientemail";
        person.gender=1;
        person.name="tstclientname";
        return person;
    }

    public void setMvc(MockMvc mvc) {
        this.mvc = mvc;
    }
}