package com.example.infrastructure.repository;

import com.example.domain.person.Person;
import com.example.spring.configuration.FeignConfiguration;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;

public interface PersonRepositoryGetAllFeign {

    @RequestLine("GET /persons?limit={limit}&offset={offset}")
    List<Person> getAll (@Param("limit") String limit, @Param("offset") String offset);
}
