package com.example.infrastructure.repository;

import com.example.domain.person.Person;
import com.example.spring.configuration.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@FeignClient(name = "rancheros", configuration = FeignConfiguration.class)
public interface PersonRepositoryFeign {

    @GET
    @Path("/persons/{id}")
    @Consumes("application/json")
    Person findById(@PathParam("id") String id);
}
