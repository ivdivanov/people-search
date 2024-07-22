package net.isbg.people.model;

public enum AddressType {
    HOME,
    WORK,
    BILL,
    SHIP,
    TEMP;
    
    public String getDescription() {
        switch (this) {
            case HOME:
                return "Home Address";
            case WORK:
                return "Work Address";
            case BILL:
                return "Billing Address";
            case SHIP:
                return "Shipping Address";
            case TEMP:
                return "Temporary Address";
            default:
                throw new IllegalArgumentException("Unknown address type: " + this);
        }
    }
}
