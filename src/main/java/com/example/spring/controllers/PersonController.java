package com.example.spring.controllers;

import com.example.domain.person.Person;
import com.example.infrastructure.repository.PersonRepositoryFeign;
import com.example.service.CreatePerson;
import com.example.service.FindByIdPerson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.time.Instant;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private FindByIdPerson findById;

    private CreatePerson create;

    @Inject
    public PersonController(FindByIdPerson findById,
                            CreatePerson create) {
        this.findById = findById;
        this.create = create;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Person findById(@PathVariable String id) {
        return findById.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Person create(@RequestBody Person person) {
        return create.create(person);
    }

    @ExceptionHandler(Exception.class)
    public void handleAllException(Exception ex, HttpServletResponse response) throws IOException {
        if (ex instanceof HystrixRuntimeException) {
            HystrixRuntimeException hystrixRuntimeException = (HystrixRuntimeException) ex;
            response.setStatus(HttpResponseStatus.NOT_FOUND.code());
            response.setContentType(MediaType.APPLICATION_JSON);
            response.getOutputStream().print(hystrixRuntimeException.getCause().getLocalizedMessage());
        } else if (ex.getCause() instanceof FeignException) {
            FeignException exception = (FeignException) ex.getCause();
            response.setStatus(exception.status());
            response.setContentType(MediaType.APPLICATION_JSON);
            response.getOutputStream().print(exception.getMessage());
        }
    }
}
