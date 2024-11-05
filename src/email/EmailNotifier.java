package email;

import observer.Observer;
import jakarta.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

public class EmailNotifier implements Observer {

    private Map<String, String> emailAddresses;

    public EmailNotifier() {
        try {
            emailAddresses = EmailFinder.getEmailMap();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void update(Object event) {
        String taskDescription = (String) ((Map) event).get("Task");
        String personName = (String) ((Map) event).get("Name");
        // end{FIXME}
        String emailAddress = emailAddresses.get(personName);

        new Thread(() -> {
            try {
                new Email(
                    emailAddress,
                    String.format("TASkOcupado: Tenés una nueva tarea!"),
                    String.format("Hola %s:\n\nSe te asignó la tarea %s.\n\nTASkOcupado,\nSaludos!", personName, taskDescription)
                ).send();
            } catch (MessagingException ex) {
                System.out.println(ex);
                System.out.println("?email not working");
            }
        }).start();
        System.out.println("[debugging] EmailNotifier update: \n" + event);
    }

}
