package com.example.service;

import com.example.domain.person.Person;
import com.example.infrastructure.repository.PersonRepositoryFeign;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observable;

public class FindAllPersonUseCase {

    @Autowired
    PersonRepositoryFeign repository;

    public Observable<Person> findAll(String offset, String limit){
        return repository.findAll(limit,offset);
    }
}
