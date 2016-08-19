package com.example.service;

import com.example.domain.person.Person;
import com.example.infrastructure.repository.PersonRepositoryFeign;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observable;

public class CreatePersonUseCase {

    @Autowired
    PersonRepositoryFeign repository;

    public Observable<Person> create (Person person){
        return repository.create(person);
    }
}
