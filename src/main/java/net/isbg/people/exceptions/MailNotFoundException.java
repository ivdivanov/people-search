package net.isbg.people.exceptions;

public class MailNotFoundException extends RuntimeException { 

    public MailNotFoundException() {
        super("The mail does not exist in our records");
    }
    
}

