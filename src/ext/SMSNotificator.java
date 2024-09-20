package ext;

import core.Notificator;

public class SMSNotificator implements Notificator {

    @Override
    public void update(Object obj) {
        notify(obj);
    }

    @Override
    public void notify(Object msg) {
        System.out.println("Soy SMSNotificator y me notificaron: \n" + msg.toString());
    }
}
