package client.view.login;

import client.model.Model;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.EventType;

import java.beans.PropertyChangeEvent;

public class LoginVM {

    private StringProperty username, password, loginReponse;
    private BooleanProperty loginButtonDisabled;
    private Model model;

    public LoginVM(Model model) {
        this.model = model;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        loginReponse = new SimpleStringProperty();
        loginButtonDisabled = new SimpleBooleanProperty(true);
        model.addPropertyChangeListener(EventType.LOGIN_RESULT.toString(), this::onLoginResponse);

        username.addListener((observableValue, oldValue, newValue) -> onUserNameChange(newValue));
    }

    private void onUserNameChange(String newValue) {
        boolean disabled = (newValue == null || newValue.equals(""));
        loginButtonDisabled.set(disabled);
    }

    private void onLoginResponse(PropertyChangeEvent evt) {
        String result = (String) evt.getNewValue();
        Platform.runLater(()-> {
            loginReponse.set(result);
        });
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty loginReponseProperty() {
        return loginReponse;
    }

    public BooleanProperty loginButtonDisabledProperty() {
        return loginButtonDisabled;
    }

    public void login() {
        model.login(username.get(), password.get());
    }
}
