package it.project.carRental.SI2001.constants;

public enum MessageEnum {
    INVALID_RENTAL("You cannot do a rent on this model 2 days before!"),
    PERMISSION_DENIED("Do login before check this page!"),
    SESSION_ERROR("You are not logged in!"),
    LOGIN_ERROR("Wrong credentials!");

    private String message;

    private MessageEnum(String message) {
       this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
