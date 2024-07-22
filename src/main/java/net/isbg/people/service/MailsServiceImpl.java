package net.isbg.people.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.isbg.people.entity.Mails;
import net.isbg.people.entity.People;
import net.isbg.people.exceptions.MailNotFoundException;
import net.isbg.people.repositoties.MailsRepository;
import net.isbg.people.repositoties.PeopleRepository;

@AllArgsConstructor
@Service
public class MailsServiceImpl implements MailsService{

    private PeopleRepository peopleRepository;
    private MailsRepository mailsRepository;

    @Override
    public void saveMail(Long personId, Mails mail) {
        Optional<People> person = peopleRepository.findById(personId);
        mail.setPeople(PeopleServiceImpl.unwrapPerson(person));
        mailsRepository.save(mail);
    }

    @Override
    public void updateMail(Long personId, Mails mail) {
        Optional<People> person = peopleRepository.findById(personId);
        mail.setPeople(PeopleServiceImpl.unwrapPerson(person));
        mail.setId(unwrapMail(mailsRepository.findById(mail.getId())).getId());
        mailsRepository.save(mail);
    }

    @Override
    public void deleteMail(Long mailId) {
        mailsRepository.deleteById(mailId);
    }

    private static Mails unwrapMail(Optional<Mails> entity) {
        if (entity.isPresent()) {
            return entity.get();
        }else {
            throw new MailNotFoundException();
        }
    }

}
