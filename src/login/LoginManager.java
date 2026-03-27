package login;

import data_management.entity.UserInfo;
import data_management.service.UserDataService;
import ADT.ListADT;

public class LoginManager {

    private UserDataService userService;

    public LoginManager(UserDataService userService) {
        this.userService = userService;
    }

    public UserInfo login(String username, String password) {

    ListADT<UserInfo> result =
        userService.search(u ->
            u.getUserName().equalsIgnoreCase(username)
            && u.checkPassword(password)
        );

    if (result.len() == 0) {
        return null;
    }

    return result.get(0);
}
}