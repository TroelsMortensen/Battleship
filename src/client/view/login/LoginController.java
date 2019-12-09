package client.view.login;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class LoginController {

    @FXML
    private Label loginResultLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private HBox buttonBox;

    private LoginVM loginVM;

    public void init(LoginVM loginVM) {
        this.loginVM = loginVM;
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        usernameTextField.textProperty().bindBidirectional(loginVM.usernameProperty());
        passwordTextField.textProperty().bindBidirectional(loginVM.passwordProperty());
        loginResultLabel.textProperty().bindBidirectional(loginVM.loginReponseProperty());

        loginVM.loginReponseProperty().addListener((observableValue, s, t1) -> onLoginResult(t1));
    }

    private void onLoginResult(String t1) {
        if("OK".equals(t1)) {
            System.out.println("Swap to lobby view");
        }
    }

    public void onLoginButton(ActionEvent actionEvent) {
        loginVM.login();
    }

    public void onRegisterButton(ActionEvent actionEvent) {
        System.out.println("Register pressed");
    }
}
