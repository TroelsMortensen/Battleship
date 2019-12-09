package client.core;

import client.view.login.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

    private Stage mainStage;
    private ViewModelFactory vmf;

    public ViewHandler(ViewModelFactory vmf) {
        mainStage = new Stage();
        this.vmf = vmf;
    }

    public void start() {
        openLoginView();
        mainStage.show();
    }

    public void openLoginView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/login/Login.fxml"));
        try {
            Parent root = loader.load();
            LoginController ctrl = loader.getController();
            ctrl.init(vmf.getLoginVM());
            mainStage.setTitle("Log in");
            Scene loginScene = new Scene(root);
            mainStage.setScene(loginScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
