package com.pink.register;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

import static  com.pink.register.Utils.*;

public class PersonRegister implements Register {
    Logger logger = Logger.getLogger(PersonRegister.class.getName());

    private static int CHILDREN_MAGIC_COUNT = 3;
    private static int CHILD_18 = 18;
    private static String FILENAME = "Persons.txt";
    private static BigInteger UNKNOWN_PERSON_ID = BigInteger.valueOf(0);
    private SortedMap<BigInteger, Person> personSortedMap = new TreeMap<>();

    public PersonRegister(){

    }

    protected void loadRegister(List<Person> personList){
        personList.stream().forEach(p ->{
            personSortedMap.put(p.getId(), p);
        });

    }

    public Collection<Person> getAllPersons(){
        return personSortedMap.values();
    }

    private int currentYear(){
        LocalDate currentdate = LocalDate.now();
        return currentdate.getYear();
    }

    public Collection<Person> getAllPersonsWithPartnerAnd3ChildrenBelow18(){
        Collection<Person> persons = personSortedMap.values();
        Set<Person> result = new HashSet<>();
        for (Person person : persons)    {
            if (person.getPartner() != null){
                if (person.getChildren() != null && person.getChildren().size() == CHILDREN_MAGIC_COUNT){
                    for (Person child: person.getChildren()){
                        if (child != null && child.dob != null && child.dob > currentYear()-CHILD_18){
                            result.add(person);
                        }
                    }
                }
            }
        }
        toBase64CSV(result);

        return result;
    }

    private String childrenToString(List<Person> children){
        StringBuilder stringBuilder = new StringBuilder();
        children.stream().forEach(p -> {
            stringBuilder.append(toBase64(p.getId().toString()) + " ");
        });

        return stringBuilder.toString();
    }

    private void writePersonToFile(Person person)  {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            FileWriter fw = new FileWriter(FILENAME, true);
            stringBuilder.append(toBase64(person.getId().toString())+",");
            stringBuilder.append(toBase64(person.getName())+",");
            stringBuilder.append(toBase64(person.getParent1().getId().toString())+",");
            stringBuilder.append(toBase64(person.getParent2().getId().toString())+",");
            stringBuilder.append(childrenToString(person.getChildren()));
            stringBuilder.append(toBase64(person.getPartner().getId().toString()));

            fw.write(stringBuilder.toString());
            fw.close();
        }
        catch(IOException e) {
            // TODO log "Unable to persist person data to file due to IOError"
        }
    }

    private void toBase64CSV(Collection<Person> personCollection){
        personCollection.stream().forEach(p -> {
            writePersonToFile(p);
        });
    }

    @Override
    public void getPerson(BigInteger id) {

    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePerson(Person person) {

    }
}
