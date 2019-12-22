package client.view.register;

import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController {
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPassword;
    @FXML
    private Label errorLabel;
    @FXML
    private Button registerButton;

    private RegisterVM registerVM;
    private ViewHandler viewHandler;

    public void init(RegisterVM rvm, ViewHandler viewHandler) {
        this.registerVM = rvm;
        this.viewHandler = viewHandler;
        userName.textProperty().bindBidirectional(rvm.usernameProperty());
        password.textProperty().bindBidirectional(rvm.passwordProperty());
        confirmPassword.textProperty().bindBidirectional(rvm.confirmPasswordProperty());
        errorLabel.textProperty().bind(rvm.registerResponseProperty());
        registerButton.disableProperty().bind(rvm.registerButtonDisabledProperty());
        rvm.registerResponseProperty().addListener((observableValue, oldValue, newValue) -> onRegisterResult(newValue));
    }

    private void onRegisterResult(String newValue) {
        if(newValue.equals("OK")) {
            registerVM.clear();
            viewHandler.openLoginView();
        }
    }

    public void onRegisterButton(ActionEvent actionEvent) {
        registerVM.registerUser();
    }

    public void onCancelButton(ActionEvent actionEvent) {
        registerVM.clear();
        viewHandler.openLoginView();
    }
}
