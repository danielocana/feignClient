package com.example.service;

import com.example.domain.person.Person;
import com.example.infrastructure.repository.PersonRepositoryFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observable;


public class FindByIdPersonUseCase {

    private static final Logger logger = LoggerFactory.getLogger(FindByIdPersonUseCase.class);
    @Autowired
    PersonRepositoryFeign repository;

    public Observable<Person> findById (String id){
        logger.info("Try to find by id = {}.", id);
        return repository.findById(id);
    }
}
