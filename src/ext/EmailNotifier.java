package ext;

import observer.Observer;
import jakarta.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

public class EmailNotifier implements Observer {
    
    private Map<String, String> membersEmails;
    
    public EmailNotifier() {
        try {
            membersEmails = EmailFinder.getEmailMap(); 
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void update(Object event) {
        String taskDescription = (String) ((Map) event).get("Task");
        String memberName = (String) ((Map) event).get("Name");
        
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
        System.out.println("[debugging] EmailNotifier update: \n" + event);
    }

}
