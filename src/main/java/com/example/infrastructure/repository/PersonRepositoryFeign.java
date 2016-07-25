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
    Person findById(@PathParam("id") String id);

    @POST
    @Path("/persons")
    @Consumes("application/json")
    Observable<Person> create(Person person);
}
