package client.view.login;

import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class LoginController {

    @FXML
    private Button loginButton;
    @FXML
    private Label loginResultLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private HBox buttonBox;



    private LoginVM loginVM;
    private ViewHandler viewHandler;

    public void init(LoginVM loginVM, ViewHandler viewHandler) {
        this.loginVM = loginVM;
        this.viewHandler = viewHandler;
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        usernameTextField.textProperty().bindBidirectional(loginVM.usernameProperty());
        passwordTextField.textProperty().bindBidirectional(loginVM.passwordProperty());
        loginResultLabel.textProperty().bindBidirectional(loginVM.loginResponseProperty());
        loginButton.disableProperty().bind(loginVM.loginButtonDisabledProperty());
        loginVM.loginResponseProperty().addListener((observableValue, oldValue, newValue) -> onLoginResult(newValue));
    }

    private void onLoginResult(String response) {
        if("OK".equals(response)) {
            System.out.println("Swap to lobby view");
        }
    }

    public void onLoginButton(ActionEvent actionEvent) {
        loginVM.login();
    }

    public void onRegisterButton(ActionEvent actionEvent) {
        loginVM.clear();
        viewHandler.openRegisterView();
    }
}
