package client.core;

import javafx.application.Application;
import javafx.stage.Stage;

public class BattleshipApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        ViewHandler viewHandler = new ViewHandler();
        viewHandler.start();
    }
}
