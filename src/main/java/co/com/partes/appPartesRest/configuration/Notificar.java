package co.com.partes.appPartesRest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class Notificar {

    @Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setBcc("mario_ruiz23151@elpoli.edu.co");
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }
}
