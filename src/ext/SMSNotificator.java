package ext;

import core.Member;
import core.Notificator;
import core.Task;

public class SMSNotificator implements Notificator {

    @Override
    public void update(Task task, Member member, Object obj) {
        notify(task, member, obj);
    }

    @Override
    public String toString() {
        return "SMS notification";
    }

    @Override
    public void notify(Task task, Member member, Object msg) {
        // TODO: how's this different from the other one?
        System.out.println("Soy SMSNotificator y me notificaron: \n" + msg.toString());
    }

}
