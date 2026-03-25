package login;

import data_management.entity.UserInfo;
import data_management.service.UserDataService;
import ADT.ListADT;

public class LoginManager {

    private UserDataService userService;

    public LoginManager(UserDataService userService) {
        this.userService = userService;
    }

    public UserInfo login(String name, int id) {
        ListADT<UserInfo> users = userService.search(
            u -> u.getName().equalsIgnoreCase(name) && u.getId() == id
        );

        if (users.len() > 0) {
            return users.get(0);
        }

        return null;
    }
}