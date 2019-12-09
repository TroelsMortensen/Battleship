package client.model;

import shared.util.PropertyChangeSubject;

public interface Model extends PropertyChangeSubject {
    void login(String username, String password);
}
