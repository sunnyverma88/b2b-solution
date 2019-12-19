package com.techieonthenet.service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;


@Service
public class EmailService {

    @Autowired
    private SendGridEmailSenderService emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


    public void sendSimpleMessage(String toEmail, String subject, Map<String, Object> valueMap, String templateName) throws MessagingException, IOException {

        Context context = new Context();
        context.setVariables(valueMap);
        String html = templateEngine.process(templateName, context);
        Content content = new Content("text/html", html);
        Mail mail = new Mail(new Email("support@Apprize.in"), subject, new Email(toEmail), content);
        emailSender.sendEmail(mail);
    }

}