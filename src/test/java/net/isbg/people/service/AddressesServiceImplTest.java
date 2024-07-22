package net.isbg.people.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.isbg.people.entity.Addresses;
import net.isbg.people.entity.People;
import net.isbg.people.exceptions.AddressNotFoundException;
import net.isbg.people.repositoties.AddressesRepository;
import net.isbg.people.repositoties.PeopleRepository;

public class AddressesServiceImplTest {

    @Mock
    private PeopleRepository peopleRepository;

    @Mock
    private AddressesRepository addressesRepository;

    @InjectMocks
    private AddressesServiceImpl addressesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAddress_Success() {
        Long personId = 1L;
        People person = new People();
        person.setId(personId);

        Addresses address = new Addresses();
        address.setId(null); // New address, ID should be null

        when(peopleRepository.findById(personId)).thenReturn(Optional.of(person));
        when(addressesRepository.save(address)).thenReturn(address);

        addressesService.saveAddress(personId, address);

        verify(peopleRepository, times(1)).findById(personId);
        verify(addressesRepository, times(1)).save(address);
        assertEquals(person, address.getPeople());
    }

    @Test
    public void testUpdateAddress_Success() {
        Long personId = 1L;
        Long addressId = 2L;

        People person = new People();
        person.setId(personId);

        Addresses address = new Addresses();
        address.setId(addressId);
        address.setPeople(person);

        Addresses existingAddress = new Addresses();
        existingAddress.setId(addressId);

        when(peopleRepository.findById(personId)).thenReturn(Optional.of(person));
        when(addressesRepository.findById(addressId)).thenReturn(Optional.of(existingAddress));
        when(addressesRepository.save(address)).thenReturn(address);

        addressesService.updateAddress(personId, address);

        verify(peopleRepository, times(1)).findById(personId);
        verify(addressesRepository, times(1)).findById(addressId);
        verify(addressesRepository, times(1)).save(address);
        assertEquals(person, address.getPeople());
    }

    @Test
    public void testUpdateAddress_NotFound() {
        Long personId = 1L;
        Addresses address = new Addresses();
        address.setId(2L);

        when(peopleRepository.findById(personId)).thenReturn(Optional.of(new People()));
        when(addressesRepository.findById(address.getId())).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundException.class, () -> addressesService.updateAddress(personId, address));
    }

    @Test
    public void testDeleteAddress() {
        Long addressId = 1L;

        doNothing().when(addressesRepository).deleteById(addressId);

        addressesService.deleteAddress(addressId);

        verify(addressesRepository, times(1)).deleteById(addressId);
    }
}
