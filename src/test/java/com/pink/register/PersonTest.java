package com.pink.register;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class PersonTest {
    private Person classUnderTest;

    @Test
    public void testGetUnknownPerson(){
        Person person = Person.getUnknownPerson();
        assertTrue(person != null);
        assertTrue(person.getParent1() != null);
        assertTrue(person.getParent2() != null);
    }

    @Test
    public void testBuilder(){
        List<Person> children = new ArrayList<>();
        Person child1 = Person.getUnknownPerson();
        child1.name("child1").dob(2011);
        children.add(child1);
        Person child2 = Person.getUnknownPerson();
        child2.name("child2").dob(2015);
        children.add(child2);
        Person builderPerson = Person.builder().name("test").parent1(Person.getUnknownPerson()).parent2(Person.getUnknownPerson()).dob(25).children(children);

        assertTrue(builderPerson != null);
        assertTrue(builderPerson.getName().equals("test"));
        assertTrue(builderPerson.getParent1() != null);
        assertTrue(builderPerson.getParent2() != null);
        assertTrue(builderPerson.getParent1().getName().equals("UnknownPerson"));
        assertTrue(builderPerson.getParent2().getName().equals("UnknownPerson"));

        assertTrue(builderPerson.getChildren().size() == children.size());
    }
}
