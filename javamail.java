import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class javamail {

    public static void main(String[] args) {

        final String username = "prasuchit@gmail.com";  //Change Accordingly
        final String password = "PASSWORD"; //Edit this

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("prasuchit@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("prasuchit@gmail.com")
            );
            message.setSubject("Testing Javamail via Gmail TLS");
            message.setText("Dear Registered User,"
                    + "\n\n This is your activation link. It expires in 24 hours!");

            Transport.send(message, message.getAllRecipients());

            System.out.println("Sent email successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}