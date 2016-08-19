package com.example.infrastructure.repository;

import com.example.domain.person.Person;
import com.example.spring.configuration.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import rx.Observable;
import javax.ws.rs.*;

@FeignClient(name = "rancheros", configuration = FeignConfiguration.class)
public interface PersonRepositoryFeign {

    @GET
    @Path("/persons/{id}")
    @Consumes("application/json")
    Observable<Person> findById(@PathParam("id") String id);

    @GET
    @Path("/persons?limit={limit}&offset={offset}")
    @Consumes("application/json")
    Observable<Person> findAll(@QueryParam("limit")String limit, @QueryParam("offset")String offset);

    @POST
    @Path("/persons")
    @Consumes("application/json")
    Observable<Person> create(Person person);

    @DELETE
    @Path("/persons/{id}")
    Observable<Void> delete(@PathParam("id") String id);

    @PUT
    @Path("/persons/{id}")
    @Consumes("application/json")
    Observable<Person> update(@PathParam("id") String id, Person person);
}
