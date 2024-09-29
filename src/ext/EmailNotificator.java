package ext;

import core.Member;
import core.NotificationDTO;
import core.Notificator;
import core.Task;
import jakarta.mail.MessagingException;

public class EmailNotificator implements Notificator {

    @Override
    public void update(NotificationDTO notificationDTO) {
        notify(notificationDTO);
    }

    @Override
    public void notify(NotificationDTO notificationDTO) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Email(
                            notificationDTO.getMember().getEmail(),
                            String.format("TASkOcupado: Tenés una nueva tarea!"),
                            String.format("Hola %s:\n\nSe te asignó la tarea %s.\n\nTASkOcupado,\nSaludos!", notificationDTO.getMember().getName(), notificationDTO.getTask().getDescription())
                    ).send();
                } catch (MessagingException ex) {
                    System.out.println(ex);
                    System.out.println("?email not working");
                }
            }
        }).start();
        //go end
        System.out.println("Soy EmailNotificator y me notificaron: \n" + notificationDTO.getMessage());
    }

    @Override
    public String toString() {
        return "Email notification";
    }

}
