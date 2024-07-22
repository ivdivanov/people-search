package net.isbg.people.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.isbg.people.entity.People;
import net.isbg.people.exceptions.PersonNotFoundException;
import net.isbg.people.repositoties.PeopleRepository;

public class PeopleServiceImplTest {

    @Mock
    private PeopleRepository peopleRepository;

    @InjectMocks
    private PeopleServiceImpl peopleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPerson_Success() {
        People person = new People();
        person.setName("Pencho Trendafilov");

        when(peopleRepository.findByNameIgnoreCase("Pencho Trendafilov")).thenReturn(Optional.of(person));

        People foundPerson = peopleService.getPerson("Pencho Trendafilov");

        assertNotNull(foundPerson);
        assertEquals("Pencho Trendafilov", foundPerson.getName());
    }

    @Test
    public void testGetPerson_NotFound() {
        when(peopleRepository.findByNameIgnoreCase("Nonexistent")).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> peopleService.getPerson("Nonexistent"));
    }

    @Test
    public void testSavePerson() {
        People person = new People();
        person.setName("Pencho Trendafilov");

        when(peopleRepository.save(person)).thenReturn(person);

        People savedPerson = peopleService.savePerson(person);

        assertNotNull(savedPerson);
        assertEquals("Pencho Trendafilov", savedPerson.getName());
        verify(peopleRepository, times(1)).save(person);
    }

    @Test
    public void testDeletePerson() {
        doNothing().when(peopleRepository).deleteByNameAllIgnoreCase("Pencho Trendafilov");

        peopleService.deletePerson("Pencho Trendafilov");

        verify(peopleRepository, times(1)).deleteByNameAllIgnoreCase("Pencho Trendafilov");
    }

    @Test
    public void testUpdatePerson_Success() {
        People existingPerson = new People();
        existingPerson.setName("Pencho Trendafilov");
        existingPerson.setPin("1234567890");

        People updatedPerson = new People();
        updatedPerson.setName("Pencho Trendafilov");
        updatedPerson.setPin("0987654321");

        when(peopleRepository.findByNameIgnoreCase("Pencho Trendafilov")).thenReturn(Optional.of(existingPerson));
        when(peopleRepository.save(existingPerson)).thenReturn(existingPerson);

        peopleService.updatePerson(updatedPerson);

        assertEquals("0987654321", existingPerson.getPin());
        verify(peopleRepository, times(1)).save(existingPerson);
    }

    @Test
    public void testUpdatePerson_NotFound() {
        People person = new People();
        person.setName("Nonexistent");

        when(peopleRepository.findByNameIgnoreCase("Nonexistent")).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> peopleService.updatePerson(person));
    }
}
