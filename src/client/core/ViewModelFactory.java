package client.core;

import client.view.login.LoginVM;

public class ViewModelFactory {

    private LoginVM loginVM;
    private ModelFactory mf;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }

    public LoginVM getLoginVM() {
        if(loginVM == null) {
            loginVM = new LoginVM(mf.getModel());
        }
        return loginVM;
    }

}
