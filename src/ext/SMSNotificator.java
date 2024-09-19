package ext;

import core.Notificator;

public class SMSNotificator implements Notificator {

    @Override
    public void update(Object arg) {
        // TODO: Preguntar
        notify(arg);
    }

    @Override
    public void notify(Object msg) {
        // TODO: how's this different from the other one?
        System.out.println("Soy SMSNotificator y me notificaron: \n" + msg.toString());
    }
}
