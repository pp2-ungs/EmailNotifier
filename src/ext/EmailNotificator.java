package ext;

import annotation.Notificator;
import observer.Observer;
import jakarta.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

@Notificator
public class EmailNotificator implements Observer {
    
    private Map<String, String> membersEmails;
    
    public EmailNotificator() {
        try {
            membersEmails = EmailFinder.getEmailMap(); 
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void update(Object event) {
        
        // begin{FIXME?}
        String taskDescription = (String) ((Map) event).get("Task");
        String memberName = (String) ((Map) event).get("Name");
        // end{FIXME}
        String memberEmail = membersEmails.get(memberName);

        new Thread(() -> {
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
        }).start();
    }

}
