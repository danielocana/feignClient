package com.example.spring.controllers;

import com.example.domain.person.Person;
import com.example.service.*;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.*;
import rx.exceptions.OnErrorNotImplementedException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private FindByIdPersonUseCase findById;

    private CreatePersonUseCase create;

    private FindAllPersonUseCase findAllPersonUseCase;

    private DeletePersonUseCase deletePersonUseCase;

    private UpdatePersonUseCase updatePersonUseCase;

    @Inject
    public PersonController(FindByIdPersonUseCase findById,
                            CreatePersonUseCase create,
                            FindAllPersonUseCase findAllPersonUseCase,
                            DeletePersonUseCase deletePersonUseCase,
                            UpdatePersonUseCase updatePersonUseCase) {
        this.findById = findById;
        this.create = create;
        this.findAllPersonUseCase = findAllPersonUseCase;
        this.deletePersonUseCase = deletePersonUseCase;
        this.updatePersonUseCase = updatePersonUseCase;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET,  produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Person findById(@PathVariable String id) {
        return findById.findById(id).toBlocking().first();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAll(@RequestParam(defaultValue = "0", required = false, name = "offset") String offset,
                                @RequestParam(defaultValue = "20", required = false, name = "limit") String limit) {
        return findAllPersonUseCase.findAll(offset, limit);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
        return create.create(person).toBlocking().first();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        //TODO when doest'n exists the person I think the observable void is a problem for the error.
        deletePersonUseCase.delete(id).subscribe();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Person update(@RequestBody Person person, @PathVariable String id) {
        person.setId(id);
        return updatePersonUseCase.update(id, person).toBlocking().first();
    }


    @ExceptionHandler(Exception.class)
    public void handleAllException(Exception ex, HttpServletResponse response) throws IOException {
        if (ex instanceof HystrixRuntimeException) {
            HystrixRuntimeException hystrixRuntimeException = (HystrixRuntimeException) ex;
            response.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
            response.setContentType(MediaType.APPLICATION_JSON);
            response.getOutputStream().print(hystrixRuntimeException.getCause().getLocalizedMessage());
        } else if (ex.getCause() instanceof FeignException) {
            FeignException exception = (FeignException) ex.getCause();
            response.setStatus(exception.status());
            response.setContentType(MediaType.APPLICATION_JSON);
            response.getOutputStream().print(exception.getMessage());
        } else if (ex instanceof OnErrorNotImplementedException) {
            response.setContentType(MediaType.APPLICATION_JSON);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.getOutputStream().print(ex.getMessage());
        }
    }
}
