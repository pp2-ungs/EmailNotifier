package ext;

import core.Observer;
import jakarta.mail.MessagingException;
import java.util.Map;

public class EmailNotificator implements Observer {

    @Override
    public void update(Object event) {

        //\begin{FIXME}
        var memberEmail = (String) ((Map) event).get("Email");
        var taskDescription = (String) ((Map) event).get("Task");
        var memberName = (String) ((Map) event).get("Name");
        //\end{FIXME}

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Email(
                            memberEmail,
                            String.format("TASkOcupado: Tenés una nueva tarea!"),
                            String.format("Hola %s:\n\nSe te asignó la tarea %s.\n\nTASkOcupado,\nSaludos!", memberName, taskDescription)
                    ).send();
                } catch (MessagingException ex) {
                    System.out.println(ex);
                    System.out.println("?email not working");
                }
            }
        }).start();
    }

}
