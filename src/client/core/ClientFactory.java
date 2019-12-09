package client.core;

import client.networking.Client;
import client.networking.DummyClient;

public class ClientFactory {

    private Client client;

    public Client getClient() {
        if(client == null) {
            client = new DummyClient();
        }
        return client;
    }

}
