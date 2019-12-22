package client.view.register;

import client.model.UserModel;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.EventType;

import java.beans.PropertyChangeEvent;

public class RegisterVM {

    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty confirmPassword = new SimpleStringProperty();
    private StringProperty registerResponse = new SimpleStringProperty();

    private BooleanProperty registerButtonDisabled = new SimpleBooleanProperty(true);

    private UserModel userModel;

    public RegisterVM(UserModel userModel) {
        this.userModel = userModel;
        userModel.addPropertyChangeListener(EventType.REGISTER_RESULT.toString(), this::onRegisterResponse);
        username.addListener((observableValue, oldValue, newValue) -> onInputFieldsUpdated());
        password.addListener((observableValue, oldValue, newValue) -> onInputFieldsUpdated());
        confirmPassword.addListener((observableValue, oldValue, newValue) -> onInputFieldsUpdated());
    }

    private void onInputFieldsUpdated() {
        boolean disabled = username.get() == null ||
                username.get().equals("") ||
                password.get() == null ||
                password.get().equals("") ||
                confirmPassword.get() == null ||
                confirmPassword.get().equals("");
        registerButtonDisabled.set(disabled);
    }

    private void onRegisterResponse(PropertyChangeEvent evt) {
        String result = (String) evt.getNewValue();
        Platform.runLater(() -> {
            registerResponse.set(result);
        });
    }

    public void registerUser() {
        String un = username.get();
        if (un == null || un.equals("")) {
            registerResponse.set("Username cannot be empty");
            return;
        }

        String pw = password.get();
        if(pw == null) {
            registerResponse.set("Password cannot be empty");
            return;
        }

        if (!pw.equals(confirmPassword.get())) {
            registerResponse.set("Passwords do not match");
            return;
        }
        userModel.registerUser(un, pw);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty confirmPasswordProperty() {
        return confirmPassword;
    }

    public BooleanProperty registerButtonDisabledProperty() {
        return registerButtonDisabled;
    }

    public StringProperty registerResponseProperty() {
        return registerResponse;
    }

    public void clear() {
        username.set("");
        password.set("");
        confirmPassword.set("");
        registerResponse.set("");
    }
}
