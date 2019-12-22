package client.model;

import shared.util.PropertyChangeSubject;

public interface UserModel extends PropertyChangeSubject {
    void login(String username, String password);

    void registerUser(String username, String password);
}
