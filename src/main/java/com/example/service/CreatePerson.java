package com.example.service;

import com.example.domain.person.Person;
import com.example.infrastructure.repository.PersonRepositoryFeign;
import org.springframework.beans.factory.annotation.Autowired;

public class CreatePerson {

    @Autowired
    PersonRepositoryFeign repository;

    public Person create (Person person){
        return repository.create(person).toBlocking().first();
    }
}
