package net.isbg.people.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.isbg.people.entity.Mails;
import net.isbg.people.entity.People;
import net.isbg.people.exceptions.MailNotFoundException;
import net.isbg.people.repositoties.MailsRepository;
import net.isbg.people.repositoties.PeopleRepository;

public class MailsServiceImplTest {

    @Mock
    private PeopleRepository peopleRepository;

    @Mock
    private MailsRepository mailsRepository;

    @InjectMocks
    private MailsServiceImpl mailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveMail_Success() {
        Long personId = 1L;
        People person = new People();
        person.setId(personId);

        Mails mail = new Mails();
        mail.setId(null); // New mail, ID should be null

        when(peopleRepository.findById(personId)).thenReturn(Optional.of(person));
        when(mailsRepository.save(mail)).thenReturn(mail);

        mailsService.saveMail(personId, mail);

        verify(peopleRepository, times(1)).findById(personId);
        verify(mailsRepository, times(1)).save(mail);
        assertEquals(person, mail.getPeople());
    }

    @Test
    public void testUpdateMail_Success() {
        Long personId = 1L;
        Long mailId = 2L;

        People person = new People();
        person.setId(personId);

        Mails mail = new Mails();
        mail.setId(mailId);
        mail.setPeople(person);

        Mails existingMail = new Mails();
        existingMail.setId(mailId);

        when(peopleRepository.findById(personId)).thenReturn(Optional.of(person));
        when(mailsRepository.findById(mailId)).thenReturn(Optional.of(existingMail));
        when(mailsRepository.save(mail)).thenReturn(mail);

        mailsService.updateMail(personId, mail);

        verify(peopleRepository, times(1)).findById(personId);
        verify(mailsRepository, times(1)).findById(mailId);
        verify(mailsRepository, times(1)).save(mail);
        assertEquals(person, mail.getPeople());
    }

    @Test
    public void testUpdateMail_NotFound() {
        Long personId = 1L;
        Mails mail = new Mails();
        mail.setId(2L);

        when(peopleRepository.findById(personId)).thenReturn(Optional.of(new People()));
        when(mailsRepository.findById(mail.getId())).thenReturn(Optional.empty());

        assertThrows(MailNotFoundException.class, () -> mailsService.updateMail(personId, mail));
    }

    @Test
    public void testDeleteMail() {
        Long mailId = 1L;

        doNothing().when(mailsRepository).deleteById(mailId);

        mailsService.deleteMail(mailId);

        verify(mailsRepository, times(1)).deleteById(mailId);
    }
}
