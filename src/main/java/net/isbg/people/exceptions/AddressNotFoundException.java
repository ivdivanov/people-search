package net.isbg.people.exceptions;

public class AddressNotFoundException extends RuntimeException { 

    public AddressNotFoundException() {
        super("The address does not exist in our records");
    }
    
}

