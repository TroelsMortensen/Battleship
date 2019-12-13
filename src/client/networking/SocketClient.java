package client.networking;

import shared.EventType;
import shared.datatransfer.Request;
import shared.datatransfer.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient implements Client{

    private PropertyChangeSupport support;
    private Socket socket;
    private ObjectOutputStream outToServer;

    public SocketClient() {
        support = new PropertyChangeSupport(this);
        start();
    }

    public void start() {
        try {
            socket = new Socket("localhost", 2910);
            new Thread(this::listenToServer).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenToServer()  {
        try {
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
            outToServer = new ObjectOutputStream(socket.getOutputStream());

            // listen for requests from the server
            while(true) {
                Request req = (Request) inFromServer.readObject();
                support.firePropertyChange(req.type.toString(), null, req.arg);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(User user) {
        Request req = new Request(EventType.LOGIN_REQUEST, user);
        try {
            outToServer.writeObject(req);
        } catch (IOException e) {
            support.firePropertyChange(EventType.LOGIN_RESULT.toString(), null, "Connection lost, restart program");
        }
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        if(null == name) {
            addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(name, listener);
            String s = "";
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
