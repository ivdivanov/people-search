package net.isbg.people.repositoties;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.isbg.people.entity.Mails;

@Repository
public interface MailsRepository extends CrudRepository<Mails, Long> {

}