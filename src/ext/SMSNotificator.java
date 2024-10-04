package ext;

import core.Observer;

// Para mí, en este proyecto debería ir una sola extensión.
// DELETEME
public class SMSNotificator implements Observer {

    @Override
    public void update(Object event) {
        System.out.println("[debuggin] SMSNotificator update: \n" + event);
    }

    @Override
    public String toString() {
        return "SMS notification";
    }

}
