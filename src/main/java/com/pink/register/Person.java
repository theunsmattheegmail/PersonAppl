package com.pink.register;

import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private static final String UNKNOWN_PERSON = "UnknownPerson";

    BigInteger id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getParent1() {
        return parent1;
    }

    public void setParent1(Person parent1) {
        this.parent1 = parent1;
    }

    public Person getParent2() {
        return parent2;
    }

    public void setParent2(Person parent2) {
        this.parent2 = parent2;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public Integer getDob() {
        return dob;
    }

    public void setDob(Integer dob) {
        this.dob = dob;
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }

    String name;
    Person parent1;
    Person parent2;
    List<Person> children;
    Integer dob; //TODO refactor to a date. For simplicity use just the year of birth
    Person partner;

    public Person(){
        children = new ArrayList<>();
    }

    public static Person builder(){
        return new Person();
    }

    public Person name(String name){
        this.setName(name);

        return this;
    }

    public Person parent1(Person person){
        this.setParent1(person);

        return this;
    }

    public Person parent2(Person person){
        this.setParent2(person);

        return this;
    }

    public Person partner(Person person){
        this.setPartner(person);

        return this;
    }

    public Person children(List<Person> children){
        this.setChildren(children);

        return this;
    }

    public Person dob(Integer dob){
        this.setDob(dob);

        return this;
    }

    public static Person getUnknownPerson(){
        Person unKnownPerson = new Person();
        unKnownPerson.setId(BigInteger.valueOf(0));
        unKnownPerson.setName(UNKNOWN_PERSON);
        unKnownPerson.setParent1(unKnownPerson);
        unKnownPerson.setParent2(unKnownPerson);

        return unKnownPerson;
    }

}
