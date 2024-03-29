package client.model;

import client.networking.Client;
import shared.EventType;
import shared.datatransfer.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserModelManager implements UserModel {

    private Client client;
    private User loggedInUser;
    private PropertyChangeSupport support;

    public UserModelManager(Client client) {
        this.client = client;
        support = new PropertyChangeSupport(this);
        client.addPropertyChangeListener(EventType.LOGIN_RESULT.toString(), this::onLoginResult);

        client.addPropertyChangeListener(EventType.REGISTER_RESULT.toString(), evt -> {
            support.firePropertyChange(evt);
        });
    }

    private void onLoginResult(PropertyChangeEvent evt) {
        String loginResult = (String) evt.getNewValue();

        if (!"OK".equals(loginResult)) {
            loggedInUser = null;
        }

        support.firePropertyChange(EventType.LOGIN_RESULT.toString(), null, loginResult);
    }

    @Override
    public void login(String username, String password) {
        loggedInUser = new User(username, password);
        client.login(loggedInUser);
    }

    @Override
    public void registerUser(String username, String password) {
        User userToCreate = new User(username, password);
        client.registerUser(userToCreate);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {

        if (null == name) {
            addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(name, listener);
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        if (name == null) {
            removePropertyChangeListener(listener);
        } else {
            support.removePropertyChangeListener(name, listener);
        }
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
