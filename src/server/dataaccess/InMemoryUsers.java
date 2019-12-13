package server.dataaccess;

import shared.datatransfer.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUsers implements UserHome {

    private List<User> users;

    public InMemoryUsers() {
        this.users = new ArrayList<>();
        users.add(new User("Troels", "Troels123"));
        users.add(new User("Jakob", "Jakob123"));
    }

    @Override
    public String validateUser(User user) {
        User u = findUser(user);
        if(u == null) return "User not found";
        if(!u.getPassword().equals(user.getPassword())) return "Incorrect password";
        return "OK";
    }

    private User findUser(User user) {
        for (User u : users) {
            if(u.getUsername().equals(user.getUsername())) {
                return u;
            }
        }
        return null;
    }
}
