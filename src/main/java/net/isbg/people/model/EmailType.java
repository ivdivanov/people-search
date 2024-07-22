package net.isbg.people.model;

public enum EmailType {
    PRSN,
    WORK,
    NEWS,
    PROMO,
    SPAM;

    public String getDescription() {
        switch (this) {
            case PRSN:
                return "Personal";
            case WORK:
                return "Work";
            case NEWS:
                return "Newsletter";
            case PROMO:
                return "Promotion";
            case SPAM:
                return "Spam";
            default:
                throw new IllegalArgumentException("Unknown email type: " + this);
        }
    }
}
