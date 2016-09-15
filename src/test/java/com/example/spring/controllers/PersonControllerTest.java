package com.example.spring.controllers;

import com.example.spring.DemoFeignApplicationTest;
import com.example.spring.common.ControllerTest;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.inject.Inject;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoFeignApplicationTest.class})
@WebAppConfiguration
public class PersonControllerTest extends ControllerTest {

    @Inject
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void findById() throws Exception {
        wireMockServer.stubFor(get(urlPathMatching("/persons/1234"))
                .willReturn(aResponse()
                        .withStatus(HttpResponseStatus.OK.code())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":\"1234\",\"name\":\"mirtinha\",\"dni\":\"the-dni\",\"phone\":\"4567888\"}")));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/persons/1234");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1234")));
    }

    @Test
    public void create() throws Exception {
        wireMockServer.stubFor(post(urlPathMatching("/persons"))
                .withRequestBody(equalToJson("{\"id\":null,\"name\":\"mirtinha\",\"dni\":\"the-dni\",\"phone\":\"4567888\"}"))
                .willReturn(aResponse()
                        .withStatus(HttpResponseStatus.CREATED.code())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":\"4567\",\"name\":\"mirtinha\",\"dni\":\"the-dni\",\"phone\":\"4567888\"}")));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"mirtinha\",\"dni\":\"the-dni\",\"phone\":\"4567888\"}");

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("4567")));
    }

    @Test
    public void deletePerson() throws Exception {
        wireMockServer.stubFor(delete(urlPathMatching("/persons/1"))
                .willReturn(aResponse()
                        .withStatus(HttpResponseStatus.OK.code())
                        .withHeader("Content-Type", "application/json")));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/persons/1");

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        wireMockServer.stubFor(put(urlPathMatching("/persons/1"))
                .willReturn(aResponse()
                        .withStatus(HttpResponseStatus.OK.code())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":\"1\",\"name\":\"ola\",\"dni\":\"the-dni\",\"phone\":\"4567888\"}")));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/persons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"ola\",\"dni\":\"the-dni\",\"phone\":\"4567888\"}");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("ola")));
    }
}