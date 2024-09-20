package ext;

import core.Member;
import core.Notificator;
import core.Task;
import jakarta.mail.MessagingException;

public class EmailNotificator implements Notificator {

    @Override
    public void update(Task task, Member member, Object obj) {
        notify(task, member, obj);
    }

    @Override
    public void notify(Task task, Member member, Object msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Email(
                            member.getEmail(),
                            String.format("TASkOcupado: Tenés una nueva tarea!"),
                            String.format("Hola %s:\n\nSe te asignó la tarea %s.\n\nTASkOcupado,\nSaludos!", member.getName(), task.getDescription())
                    ).send();
                } catch (MessagingException ex) {
                    System.out.println(ex);
                    System.out.println("?email not working");
                }
            }
        }).start();
        //go end
        System.out.println("Soy EmailNotificator y me notificaron: \n" + msg.toString());
    }

    @Override
    public String toString() {
        return "Email notification";
    }

}
