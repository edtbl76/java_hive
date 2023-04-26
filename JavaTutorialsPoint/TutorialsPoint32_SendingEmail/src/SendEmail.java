import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    /*
        NOTE: this only works if you have an smtp server running on port 25.

        You also need javamail API 1.2
        and JAF 1.1.1
     */
    public static void main(String[] args) {

        // Recipient's email ID
        String to = "allmusic76@gmail.com";
        String from = "allmusic76@gmail.com";

        String host = "localhost";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Subject Line");
            message.setText("Here is the message!");

            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
