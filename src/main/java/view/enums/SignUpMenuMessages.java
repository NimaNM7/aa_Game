package view.enums;

public enum SignUpMenuMessages {
    USERNAME_EXISTS("This username already exists!"),
    INVALID_USERNAME("Username is not valid! Just use Letters, Numbers and Underline!"),
    WEAK_PASSWORD("Weak Password!"),
    EMPTY_USERNAME("Username field must be filled!"),
    EMPTY_PASSWORD("Password field must be filled!"),
    EMPTY_CONFIRMATION("Password confirmation field must be filled!"),
    PASSWORD_CONFIRMATION_WRONG("Password and confirmation aren't the same!"),
    SIGN_UP_SUCCESSFUL("Sign Up was successful!"),
    ;
    private final String message;

    SignUpMenuMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
