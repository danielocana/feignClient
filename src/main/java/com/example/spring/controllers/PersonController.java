package com.example.spring.controllers;

import com.example.domain.person.Person;
import com.example.infrastructure.repository.PersonRepositoryFeign;
import com.example.service.FindByIdPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private FindByIdPerson findById;

    @Inject
    public PersonController (FindByIdPerson findById){
        this.findById = findById;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Person findById(@PathVariable String id){
        return findById.findById(id);
    }

}
