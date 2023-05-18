package view.enums;

public enum ProfileMenuMessages {
    THE_SAME("this is the same amount as before"),
    USERNAME_INVALID("new username is invalid"),
    PASSWORD_WEAK("password is weak"),
    DONE("done"),
    ;

    private final String message;

    ProfileMenuMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
