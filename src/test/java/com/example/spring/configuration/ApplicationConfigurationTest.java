package com.example.spring.configuration;


import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("test")
public class ApplicationConfigurationTest {

    private static final int wireMockPort = 8089;

    @Bean
    public ServerList<Server> ribbonServerList() {
        return new StaticServerList<>(
                new Server("localhost", wireMockPort)
        );
    }
}
