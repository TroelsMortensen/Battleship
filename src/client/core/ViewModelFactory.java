package client.core;

import client.view.login.LoginVM;
import client.view.register.RegisterVM;

public class ViewModelFactory {

    private LoginVM loginVM;
    private ModelFactory mf;
    private RegisterVM registerVM;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }

    public LoginVM getLoginVM() {
        if(loginVM == null) {
            loginVM = new LoginVM(mf.getUserModel());
        }
        return loginVM;
    }

    public RegisterVM getRegisterVM() {
        if(registerVM == null) {
            registerVM = new RegisterVM(mf.getUserModel());
        }
        return registerVM;
    }

}
