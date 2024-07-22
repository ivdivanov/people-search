package net.isbg.people.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.isbg.people.entity.Mails;
import net.isbg.people.service.MailsService;


@AllArgsConstructor
@RestController
@RequestMapping("/mails")
public class MailsController {

    MailsService mailsService;

    @PostMapping
    public ResponseEntity<Mails> saveMail(@RequestParam Long personId, @Valid @RequestBody Mails mail) {
        mailsService.saveMail(personId, mail);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Mails> updateMail(@RequestParam Long personId, @Valid @RequestBody Mails mail) {
        mailsService.updateMail(personId, mail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Mails> deleteMail(@RequestParam Long mailId) {
        mailsService.deleteMail(mailId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
