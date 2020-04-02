package com.techieonthenet.service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.techieonthenet.entity.Order;
import com.techieonthenet.entity.TaskItem;
import com.techieonthenet.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * The type Email service.
 */
@Service
public class EmailService {

    @Autowired
    private SendGridEmailSenderService emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${base.url.path}")
    private String contextPath;


    /**
     * Send simple message.
     *
     * @param toEmail      the to email
     * @param subject      the subject
     * @param valueMap     the value map
     * @param templateName the template name
     * @throws MessagingException the messaging exception
     * @throws IOException        the io exception
     */
    public void sendSimpleMessage(String toEmail, String subject, Map<String, Object> valueMap, String templateName) throws MessagingException, IOException {

        Context context = new Context();
        context.setVariables(valueMap);
        String html = templateEngine.process(templateName, context);
        Content content = new Content("text/html", html);
        Mail mail = new Mail(new Email("support@Apprize.in"), subject, new Email(toEmail), content);
        emailSender.sendEmail(mail);
    }

    public void sendOrderConfirmationEmail(Order order , String level1Approver , String level2Approver) throws IOException, MessagingException {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("order", order);
        valueMap.put("level1Approver", level1Approver);
        valueMap.put("level2Approver", level2Approver) ;
        valueMap.put("orderDetailUrl", contextPath.concat("order/").concat(order.getId().toString()).concat("/details"));
        sendSimpleMessage(order.getUser().getEmail(), "Apprize - Thank you for your order -  Order # " + order.getId(), valueMap, "order-confirm");
    }

    public void sendOrderUpdatemail(Order order, TaskItem task , User user) throws IOException, MessagingException {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("order", order);
        valueMap.put("task" , task);
        valueMap.put("approver" , user.getFirstName().concat(" ").concat(user.getLastName()));
        valueMap.put("orderDetailUrl" , contextPath.concat("order/").concat(order.getId().toString()).concat("/details") );
        sendSimpleMessage(order.getUser().getEmail(), "Apprize - Order # "+ order.getId() +" Updated", valueMap, "order-update");
    }

    public void sendTaskAssignmentEmail(Order order , TaskItem task) throws MessagingException, IOException
    {
        for (User user : task.getUsers()) {
            Map<String, Object> valueMap = new HashMap<>();
            valueMap.put("order", order);
            valueMap.put("orderDetailUrl" , contextPath.concat("order/").concat(order.getId().toString()).concat("/details") );
            valueMap.put("orderApprovalUrl" , contextPath.concat("task/pending"));
            valueMap.put("user" , user);
            sendSimpleMessage(user.getEmail(), "Apprize - Order # " + order.getId() + " - Requires your attention", valueMap, "task-assign");

        }
    }

}