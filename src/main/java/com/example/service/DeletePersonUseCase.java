package com.example.service;

import com.example.infrastructure.repository.PersonRepositoryFeign;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observable;

public class DeletePersonUseCase {

    @Autowired
    PersonRepositoryFeign repository;

    public Observable<Void> delete(String id){
        return repository.delete(id);
    }
}
