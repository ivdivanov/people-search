package net.isbg.people.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.isbg.people.entity.Addresses;
import net.isbg.people.entity.Mails;
import net.isbg.people.entity.People;
import net.isbg.people.exceptions.PersonNotFoundException;
import net.isbg.people.repositoties.PeopleRepository;

@AllArgsConstructor
@Service
public class PeopleServiceImpl implements PeopleService {

    PeopleRepository peopleRepository;
    
    @Override
    public People getPerson(String name) {
        Optional<People> person = peopleRepository.findByNameIgnoreCase(name);
        return unwrapPerson(person);
    }

    @Override
    @Transactional
    public People savePerson(People person) {
        if (person.getMails() != null && !person.getMails().isEmpty()) {
            for (Mails email : person.getMails()) {
                email.setPeople(person);
            }
        }
        if (person.getAddresses() != null && !person.getAddresses().isEmpty()) {
            for (Addresses address : person.getAddresses()) {
                address.setPeople(person);
            }
        }

        return peopleRepository.save(person);
    }

    @Override
    public void deletePerson(String name) {
        peopleRepository.deleteByNameAllIgnoreCase(name);
    }

    @Override
    @Transactional
    public void updatePerson(People person) {
        People existingPerson = unwrapPerson(peopleRepository.findByNameIgnoreCase(person.getName()));
        existingPerson.setPin(person.getPin());
        peopleRepository.save(existingPerson);
    }

    protected static People unwrapPerson(Optional<People> entity) {
        if (entity.isPresent()) {
            return entity.get();
        }else {
            throw new PersonNotFoundException();
        }
    }
    
}
