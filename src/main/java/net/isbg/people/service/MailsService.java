package net.isbg.people.service;

import net.isbg.people.entity.Mails;

public interface MailsService {
    void saveMail(Long personId, Mails mail);
    void updateMail(Long PersonId, Mails mails);
    void deleteMail(Long mailId);
}
