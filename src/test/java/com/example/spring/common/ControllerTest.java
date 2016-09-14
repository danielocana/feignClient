package com.example.spring.common;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class ControllerTest {
    protected static WireMockServer wireMockServer;

    @BeforeClass
    public static void setUpBefore() throws Exception {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
    }

    @AfterClass
    public static void tearDownAfter() throws Exception {
        wireMockServer.shutdown();
        wireMockServer.stop();
    }
}
