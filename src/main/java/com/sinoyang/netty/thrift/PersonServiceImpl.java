package com.sinoyang.netty.thrift;

import com.sinoyang.netty.thrift.generated.DataException;
import com.sinoyang.netty.thrift.generated.Person;
import com.sinoyang.netty.thrift.generated.PersonService;
import org.apache.thrift.TException;

public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("getPersonByUsername(" + username + ").......");
        Person person = new Person();
        person.setUsername("李四");
        person.setAge(18);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("savePerson().......");
        System.out.println(person.getUsername() + ", " + person.getAge() + ", " + person.isMarried());
    }
}