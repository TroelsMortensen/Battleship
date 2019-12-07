package client.view.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;

    public void init() {

    }

    public void OnLoginButton(ActionEvent actionEvent) {
        System.out.println("Login pressed");
    }

    public void onRegisterButton(ActionEvent actionEvent) {
        System.out.println("Register pressed");
    }
}
