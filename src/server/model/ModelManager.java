package server.model;

import server.dataaccess.UserHome;
import shared.datatransfer.User;

public class ModelManager implements GameModel {

    private UserHome userHome;

    public ModelManager(UserHome userHome) {
        this.userHome = userHome;
    }

    @Override
    public String validateUser(User user) {
        return userHome.validateUser(user);
    }
}
