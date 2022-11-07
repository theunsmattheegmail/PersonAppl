package com.pink.register;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class PersonRegisterTest {
    private PersonRegister classUnderTest = new PersonRegister();

    @Before
    public void setup() {
        classUnderTest = new PersonRegister();
    }

    @Test
    public void testLoadRegister(){

        List<Person> personList = new ArrayList<>();

        for (int i=0;i<10;i++) {
            Person person = Person.getUnknownPerson();
            person.setId(Utils.getNextId());
            person.setName("personName" + i);
            person.setParent1(Person.getUnknownPerson());
            person.setParent2(Person.getUnknownPerson());
            person.setPartner(null);
            List<Person> children = new ArrayList<>();
            person.setChildren(children);
            personList.add(person);
        }

        classUnderTest.loadRegister(personList);
        Collection<Person> allPersons = classUnderTest.getAllPersons();
        assertTrue(allPersons != null && allPersons.size() == 10) ;
    }

    private List<Person> addChildren(int cnt){
        List<Person> children = new ArrayList<>();
        for (int i=0;i<cnt;i++) {
            Person child = Person.getUnknownPerson();
            child.dob(2011);
            children.add(child);
        }
        return children;
    }

    @Test
    public void testGetAllPersonsWithPartnerAnd3ChildrenBelow18(){

        List<Person> personList = new ArrayList<>();

        for (int i=0;i<10;i++) {
            Person person = Person.getUnknownPerson();
            person.setId(Utils.getNextId());
            person.setName("personName" + i);
            person.setParent1(Person.getUnknownPerson());
            person.setParent2(Person.getUnknownPerson());
            person.setPartner(null);
            List<Person> children = new ArrayList<>();
            person.setChildren(children);
            personList.add(person);
        }

        classUnderTest.loadRegister(personList);

        // Select 4 to have 3 children
        personList.get(4).setChildren(addChildren(3));
        personList.get(4).setPartner(Person.getUnknownPerson());

        classUnderTest.loadRegister(personList);
        Collection<Person> personList1 = classUnderTest.getAllPersonsWithPartnerAnd3ChildrenBelow18();
        assertTrue(personList1 != null && personList1.size() == 1) ;
    }
}
