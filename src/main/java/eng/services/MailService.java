package eng.services;

import lombok.Data;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * сервис для отправки сообщений
 */
@Data
public class MailService {
    private Properties properties ;
    private  String messageTo;

    public MailService() {
        this.properties = System.getProperties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/application.properties");
            properties.load(fileInputStream);
            this.messageTo = properties.getProperty("mail.smtp.to");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * отправить сообщение по Email получателю указанному в поле messageTo
     * @param title заголовок сообщения
     * @param message текст сообщения
     */
    public void sendEmailMessage(String title, String message){
        Session session  = Session.getInstance(
                properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(properties.getProperty("mail.smtp.from"),
                                properties.getProperty("mail.smtp.password"));
                    }
                });

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(properties.getProperty("mail.smtp.from"));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(messageTo));
            if (title!=null){
                mimeMessage.setSubject(title);
            }
            if (message!=null){
                mimeMessage.setText(message);
            }
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
