package shared.datatransfer;

import shared.EventType;

import java.io.Serializable;

public class Request implements Serializable {

    public EventType type;
    public Object arg;

    public Request(EventType type, Object arg) {
        this.type = type;
        this.arg = arg;
    }
}
