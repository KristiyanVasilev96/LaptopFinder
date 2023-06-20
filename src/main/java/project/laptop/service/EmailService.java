package project.laptop.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
public class EmailService {

    private final TemplateEngine templateEngine;
    private  final JavaMailSender javaMailSender;
    private final MessageSource messageSource;

    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender, MessageSource messageSource) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
    }
    public void sendRegistrationEmail(
            String userEmail,
            String userName,
            Locale preferLocale) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("laptopFinder@abv.com");
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject(getEmailSubject(preferLocale));
            mimeMessageHelper.setText(generateMessageContent(preferLocale,userName), true);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {

            throw new RuntimeException(e);
        }


    }

    private String generateMessageContent(Locale locale, String userName) {
        Context ctx = new Context();
        ctx.setVariable("userName", userName);
        ctx.setLocale(locale);
        return templateEngine.process("email/registration", ctx);
    }

    private String getEmailSubject(Locale locale){ //change email hog subject from en to bg
        return messageSource.getMessage("registration_subject",new Object[0],locale);
    }
}
