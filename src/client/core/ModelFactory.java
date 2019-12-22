package client.core;

import client.model.UserModel;
import client.model.UserModelManager;

public class ModelFactory {

    private UserModel userModel;
    private ClientFactory cf;

    public ModelFactory(ClientFactory cf) {
        this.cf = cf;
    }

    public UserModel getUserModel() {
        if(userModel == null) {
            userModel = new UserModelManager(cf.getClient());
        }
        return userModel;
    }

}
