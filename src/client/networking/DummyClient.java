package client.networking;

import shared.EventType;
import shared.datatransfer.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class DummyClient implements Client {

    private List<User> users = new ArrayList<>();
    private PropertyChangeSupport support;

    public DummyClient() {
        users.add(new User("Troels", "Troels123"));
        users.add(new User("Jakob", "Jakob123"));
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void login(User user) {
        System.out.println("CLient: " + user);
        String result = "";
        boolean userFound = false;
        for (User u : users) {
            if(u.getUsername().equals(user.getUsername())) {
                if(u.getPassword().equals(user.getPassword())) {
                    result = "OK";
                } else {
                    result = "Incorrect password";
                }
                userFound = true;
            }
        }
        if(!userFound){
            result = "User not found";
        }
        support.firePropertyChange(EventType.LOGIN_RESULT.toString(), null, result);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        if(null == name) {
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
        if(name == null) {
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
