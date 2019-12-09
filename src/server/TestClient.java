package server;

import shared.EventType;
import shared.datatransfer.Request;
import shared.datatransfer.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TestClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 2910);
        User u1 = new User("Troels1", "Troels123");
        User u2 = new User("Troels", "Troels12");
        User u3 = new User("Troels", "Troels123");


        ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());

        outToServer.writeObject(new Request(EventType.LOGIN_REQUEST, u1));
        Request request = (Request) inFromServer.readObject();
        System.out.println(request.arg); // user not found

        outToServer.writeObject(new Request(EventType.LOGIN_REQUEST, u2));
        Request request1 = (Request) inFromServer.readObject();
        System.out.println(request1.arg); // Incorrect password

        outToServer.writeObject(new Request(EventType.LOGIN_REQUEST, u3));
        Request request2 = (Request) inFromServer.readObject();
        System.out.println(request2.arg); // OK
    }
}
