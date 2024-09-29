package ext;

import core.Member;
import core.NotificationDTO;
import core.Notificator;
import core.Task;

// Para mí, en este proyecto debería ir una sola extensión.
// DELETEME
public class SMSNotificator implements Notificator {

    @Override
    public void update(NotificationDTO notificationDTO) {
        notify(notificationDTO);
    }

    @Override
    public void notify(NotificationDTO notificationDTO) {
        // TODO: how's this different from the other one?
        System.out.println("Soy SMSNotificator y me notificaron: \n" + notificationDTO.getMessage());
    }

    @Override
    public String toString() {
        return "SMS notification";
    }

}
