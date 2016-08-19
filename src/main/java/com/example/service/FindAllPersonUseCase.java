package com.example.service;

import com.example.domain.person.Person;
import com.example.infrastructure.repository.PersonRepositoryFeign;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observable;

import java.util.List;

public class FindAllPersonUseCase {

    @Autowired
    PersonRepositoryFeign repository;

    public List<Person> findAll(String offset, String limit){
        return repository.findAll();
    }
}
