package com.example.service;

import com.example.domain.person.Person;
import com.example.infrastructure.repository.PersonRepositoryFeign;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observable;

import javax.inject.Inject;

public class FindByIdPersonUseCase {

    @Autowired
    PersonRepositoryFeign repository;

    public Observable<Person> findById (String id){
        return repository.findById(id);
    }
}
