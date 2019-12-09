package server.networking;

import server.model.GameModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BattleshipServer {

    private GameModel gameModel;

    public BattleshipServer(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(2910);
        System.out.println("Server started..");
        while(true) {
            try {
                System.out.println("Waiting for client..");
                Socket client = serverSocket.accept();
                ServerSocketHandler ssh = new ServerSocketHandler(gameModel, client);
                (new Thread(ssh)).start();
                System.out.println("Client connected");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
