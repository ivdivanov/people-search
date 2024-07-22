package net.isbg.people.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.isbg.people.entity.People;
import net.isbg.people.service.PeopleService;

@AllArgsConstructor
@RestController
@RequestMapping("/people")
public class PeopleController {

    PeopleService peopleService;

    @GetMapping
    public ResponseEntity<People> getPerson(@RequestParam String name) {
        return new ResponseEntity<>(peopleService.getPerson(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<People> savePerson(@Valid @RequestBody People person) {
        peopleService.savePerson(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<People> updatePerson(@Valid @RequestBody People person) {
        peopleService.updatePerson(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<People> deletePerson(@RequestParam String name) {
        peopleService.deletePerson(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}