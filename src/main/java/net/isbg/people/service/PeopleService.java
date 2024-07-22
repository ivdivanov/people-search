package net.isbg.people.service;

import net.isbg.people.entity.People;

public interface PeopleService {
    People getPerson(String name);
    People savePerson(People person);
    void deletePerson(String name);
    void updatePerson(People person);
    
}
