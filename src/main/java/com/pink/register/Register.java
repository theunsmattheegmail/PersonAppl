package com.pink.register;

import java.math.BigInteger;

public interface Register {
    public void getPerson(BigInteger id);
    public void updatePerson(Person person);
    public void deletePerson(Person person);

}
