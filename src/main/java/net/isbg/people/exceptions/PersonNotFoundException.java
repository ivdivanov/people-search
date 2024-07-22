package net.isbg.people.exceptions;

public class PersonNotFoundException extends RuntimeException { 

    public PersonNotFoundException() {
        super("No such person exists in our records");
    }
    
}

