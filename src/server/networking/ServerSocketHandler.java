package server.networking;

import server.model.GameModel;
import shared.EventType;
import shared.datatransfer.Request;
import shared.datatransfer.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable {

    private GameModel gameModel;
    private Socket socket;
    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;

    public ServerSocketHandler(GameModel gameModel, Socket socket) throws IOException {
        this.gameModel = gameModel;
        this.socket = socket;
        outToClient = new ObjectOutputStream(socket.getOutputStream());
        inFromClient = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {

                Request request = (Request) inFromClient.readObject();
                if (request.type == EventType.LOGIN_REQUEST) {
                    System.out.println("Login requested");
                    User user = (User)request.arg;
                    String loginResult = gameModel.validateUser(user);
                    System.out.println("   -- result: " + loginResult);
                    Request response = new Request(EventType.LOGIN_RESULT, loginResult);
                    outToClient.writeObject(response);
                }

            } catch (Exception e) {

            }
        }
    }
}
