package server;

import server.dataaccess.InMemoryUsers;
import server.dataaccess.UserHome;
import server.model.GameModel;
import server.model.ModelManager;
import server.networking.BattleshipServer;

import java.io.IOException;

public class RunBattleshipServer {

    public static void main(String[] args) {
        UserHome uh = new InMemoryUsers();
        GameModel gm = new ModelManager(uh);
        BattleshipServer bs = new BattleshipServer(gm);
        try {
            bs.startServer();
        } catch (IOException e) {
            System.out.println("Complete failure to launch");
        }
    }

}
