package com.example.service;

import com.example.domain.person.Person;
import com.example.infrastructure.repository.PersonRepositoryFeign;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observable;

public class UpdatePersonUseCase {

    @Autowired
    PersonRepositoryFeign repository;

    public Observable<Person> update (String id, Person person){
        return repository.update(id, person);
    }
}
