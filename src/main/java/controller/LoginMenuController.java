package controller;

import model.User;
import view.enums.LoginMenuMessages;

public class LoginMenuController {
    private static int numberofGuestPlayers;
    public static LoginMenuMessages login(String username, String password) {
        User myUser;
        if (username.length() == 0) return LoginMenuMessages.EMPTY_USERNAME;
        if (password.length() == 0) return LoginMenuMessages.EMPTY_PASSWORD;
        if ((myUser = UserController.findUserWithUsername(username)) == null) {
            return LoginMenuMessages.USERNAME_DOESNT_EXIST;
        }
        if (!password.equals(myUser.getPassword())) {
            return LoginMenuMessages.INCORRECT_PASSWORD;
        }
        UserController.setCurrentUser(myUser);
        return LoginMenuMessages.LOGIN_DONE;
    }


    public static void playAsGuest() {
        UserController.setCurrentUser(new User("guest" + numberofGuestPlayers, "11111",true));
    }
}
