package ext;

import core.Notificator;

public class EmailNotificator implements Notificator {

    @Override
    public void update(Object obj) {
        notify(obj);
    }

    @Override
    public void notify(Object msg) {
        System.out.println("Soy EmailNotificator y me notificaron: \n" + msg.toString());
    }

}
