package view.enums;

public enum LoginMenuMessages {
    USERNAME_DOESNT_EXIST("This Username doesn't exist!"),
    INCORRECT_PASSWORD("This password is incorrect!"),
    LOGIN_DONE("You logged in successfully!"),
    EMPTY_USERNAME("Username field is empty!"),
    EMPTY_PASSWORD("Password field is empty!"),
    ;
    private final String message;

    LoginMenuMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
