package client.core;

import client.view.login.LoginController;
import client.view.register.RegisterController;
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
            ctrl.init(vmf.getLoginVM(), this);
            mainStage.setTitle("Log in");
            Scene loginScene = new Scene(root);
            mainStage.setScene(loginScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openRegisterView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/register/Register.fxml"));
        try {
            Parent root = loader.load();
            RegisterController ctrl = loader.getController();
            ctrl.init(vmf.getRegisterVM(), this);
            mainStage.setTitle("Register");
            Scene loginScene = new Scene(root);
            mainStage.setScene(loginScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
