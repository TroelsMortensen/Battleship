package client.view.login;

import client.model.UserModel;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.EventType;

import java.beans.PropertyChangeEvent;

public class LoginVM {

    private StringProperty username, password, loginResponse;
    private BooleanProperty loginButtonDisabled;
    private UserModel userModel;

    public LoginVM(UserModel userModel) {
        this.userModel = userModel;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        loginResponse = new SimpleStringProperty();
        loginButtonDisabled = new SimpleBooleanProperty(true);
        userModel.addPropertyChangeListener(EventType.LOGIN_RESULT.toString(), this::onLoginResponse);

        username.addListener((observableValue, oldValue, newValue) -> onUserNameChange());
        password.addListener((observableValue, oldValue, newValue) -> onUserNameChange());
    }

    private void onUserNameChange() {
        boolean disabled =  username.get() == null      ||
                            username.get().equals("")   ||
                            password.get() == null      ||
                            password.get().equals("");
        loginButtonDisabled.set(disabled);
    }

    private void onLoginResponse(PropertyChangeEvent evt) {
        String result = (String) evt.getNewValue();
        Platform.runLater(()-> {
            loginResponse.set(result);
        });
    }

    StringProperty usernameProperty() {
        return username;
    }

    StringProperty passwordProperty() {
        return password;
    }

    StringProperty loginResponseProperty() {
        return loginResponse;
    }

    BooleanProperty loginButtonDisabledProperty() {
        return loginButtonDisabled;
    }

    public void login() {
        userModel.login(username.get(), password.get());
    }

    public void clear() {
        username.set("");
        password.set("");
        loginResponse.set("");
    }
}
