package fit.cvut.EventPro.controller;

import fit.cvut.EventPro.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired private EmailService emailService;

    // Sending a simple Email
//    @PostMapping("/sendMail")
//    public String
//    sendMail()
//    {
////        String to, String from, String subject, String body
////        emailService.sendEmail("salmanchanna0314@gmail.com", "gtest9188@gmail.com", "abc", "body text");
//        try {
//            emailService.sendHTMLMailToken();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//        return "1";
//    }

//    // Sending email with attachment
//    @PostMapping("/sendMailWithAttachment")
//    public String sendMailWithAttachment(
//            @RequestBody EmailDetails details)
//    {
//        String status
//                = emailService.sendMailWithAttachment(details);
//
//        return status;
//    }
}
