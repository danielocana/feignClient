package com.example.infrastructure.repository;

import com.example.domain.person.Person;
import com.example.spring.configuration.FeignConfiguration;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import rx.Observable;

import javax.ws.rs.*;
import java.util.List;

@FeignClient(name = "rancheros", configuration = FeignConfiguration.class)
public interface PersonRepositoryFeign {

    @GET
    @Path("/persons/{id}")
    @Consumes("application/json")
    Person findById(@PathParam("id") String id);

    @GET
    @Path("/persons?{pagination}")
    @Consumes("application/json")
    Observable<Person> findAll(@Param("pagination")String pagination);

    @GET
    @Path("/persons")
    @Consumes("application/json")
    List<Person> findAll();

    @POST
    @Path("/persons")
    @Consumes("application/json")
    Observable<Person> create(Person person);
}
