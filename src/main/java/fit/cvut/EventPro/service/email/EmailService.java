package fit.cvut.EventPro.service.email;


import fit.cvut.EventPro.EmailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Service
@Slf4j
public class EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailComponentImpl emailComponent;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    private EmailConfig globalConfiguration;

    public void sendEmail(String to, String from, String subject, String body) throws MailException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
    }

    public void sendHTMLMailToken(String eventName, String invitationSentBy, String emailTo, Long invitationId, Long contactId) throws MessagingException {

        Context context = new Context();
        context.setVariable("content1", eventName);
        context.setVariable("content2", invitationSentBy+" has invited you, click on accept to accept invitation.");
        context.setVariable("link", globalConfiguration.getBaseUrl()+"/invitation/email/"+invitationId+"/ACCEPTED");
        context.setVariable("link2", globalConfiguration.getBaseUrl()+"/invitation/email/"+invitationId+"/REJECTED");
        //html template and pass variables to it
        String body = templateEngine.process("verification", context);
        //finally send email
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(globalConfiguration.getUser());
        helper.setTo(emailTo);
        helper.setSubject("Event invitation");
        helper.setText(body, true);
        javaMailSender.send(message);

    }

}
