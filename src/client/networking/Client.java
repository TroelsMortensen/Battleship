package client.networking;

import shared.datatransfer.User;
import shared.util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject {
    void login(User user);
}
