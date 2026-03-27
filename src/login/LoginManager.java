package login;

import data_management.entity.UserInfo;
import data_management.service.UserDataService;
import ADT.ListADT;

public class LoginManager {

    private UserDataService userService;

    public LoginManager(UserDataService userService) {
        this.userService = userService;
    }

    public UserInfo login(String name, String password) {

    ADT.ListADT<UserInfo> result =
        userService.search(u ->
            u.getName().equalsIgnoreCase(name)
            && u.checkPassword(password)
        );

    if (result.len() == 0) {
        return null;
    }

    return result.get(0);
}
}