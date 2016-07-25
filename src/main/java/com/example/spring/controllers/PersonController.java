package com.example.spring.controllers;

import com.example.domain.person.Person;
import com.example.infrastructure.repository.PersonRepositoryFeign;
import com.example.service.CreatePerson;
import com.example.service.FindByIdPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private FindByIdPerson findById;

    private CreatePerson create;

    @Inject
    public PersonController (FindByIdPerson findById,
                             CreatePerson create){
        this.findById = findById;
        this.create = create;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Person findById(@PathVariable String id){
        return findById.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Person create(@RequestBody Person person){
        return create.create(person);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleAllException(Exception ex) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
