package com.techieonthenet.service;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * The type Send grid email sender service.
 */
@Service
public class SendGridEmailSenderService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    /**
     * Send email.
     *
     * @param mail the mail
     * @throws IOException the io exception
     */
    public void sendEmail(Mail mail) throws IOException {

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            System.out.println("Request :" + request.getHeaders());
            Response response = sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }
}
