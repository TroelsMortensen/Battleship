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

    private StringProperty username, password, loginResponse;
    private BooleanProperty loginButtonDisabled;
    private Model model;

    public LoginVM(Model model) {
        this.model = model;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        loginResponse = new SimpleStringProperty();
        loginButtonDisabled = new SimpleBooleanProperty(true);
        model.addPropertyChangeListener(EventType.LOGIN_RESULT.toString(), this::onLoginResponse);

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

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty loginResponseProperty() {
        return loginResponse;
    }

    public BooleanProperty loginButtonDisabledProperty() {
        return loginButtonDisabled;
    }

    public void login() {
        model.login(username.get(), password.get());
    }
}
