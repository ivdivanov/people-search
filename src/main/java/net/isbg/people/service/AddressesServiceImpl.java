package net.isbg.people.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.isbg.people.entity.Addresses;
import net.isbg.people.entity.People;
import net.isbg.people.exceptions.AddressNotFoundException;
import net.isbg.people.repositoties.AddressesRepository;
import net.isbg.people.repositoties.PeopleRepository;

@AllArgsConstructor
@Service
public class AddressesServiceImpl implements AddressesService{

    private PeopleRepository peopleRepository;
    private AddressesRepository addressesRepository;

    @Override
    public void saveAddress(Long personId, Addresses address) {
        Optional<People> person = peopleRepository.findById(personId);
        address.setPeople(PeopleServiceImpl.unwrapPerson(person));
        addressesRepository.save(address);
    }

    @Override
    public void updateAddress(Long personId, Addresses address) {
        Optional<People> person = peopleRepository.findById(personId);
        address.setPeople(PeopleServiceImpl.unwrapPerson(person));
        address.setId(unwrapAddress(addressesRepository.findById(address.getId())).getId());
        addressesRepository.save(address);
    }

    @Override
    public void deleteAddress(Long addressId) {
        addressesRepository.deleteById(addressId);
    }

    private static Addresses unwrapAddress(Optional<Addresses> entity) {
        if (entity.isPresent()) {
            return entity.get();
        }else {
            throw new AddressNotFoundException();
        }
    }
    
}
