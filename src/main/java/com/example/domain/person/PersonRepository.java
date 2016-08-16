package com.example.domain.person;

import java.util.List;

public interface PersonRepository {

    Person findById (String id);

    List<Person> findAll (String offset, String limit);
}
