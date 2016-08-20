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
    Observable<Person> findById(@PathParam("id") String id);

    @GET
    @Path("/persons")
    @Consumes("application/json")
    List<Person> findAll();

    @POST
    @Path("/persons")
    @Consumes("application/json")
    Observable<Person> create(Person person);

    @DELETE
    @Path("/persons/{id}")
    @Consumes("application/json")
    Observable<Void> delete(@PathParam("id") String id);

    @PUT
    @Path("/persons/{id}")
    @Consumes("application/json")
    Observable<Person> update(@PathParam("id") String id, Person person);
}
