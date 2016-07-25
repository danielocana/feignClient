package com.example.domain.person;

public class Person {
    private String id;
    private String name;
    private String dni;
    private String phone;

    public Person() {
    }

    public Person(String id, String name, String dni, String phone) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDni() {
        return dni;
    }

    public String getPhone() {
        return phone;
    }
}
