package com.bian.mail.service;

import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.exceptions.MailerSendException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final MailerSend mailerSend;

    public EmailService(@Value("${mailersend.api.token}") String apiToken) {
        this.mailerSend = new MailerSend();
        this.mailerSend.setToken(apiToken);
    }

    public String sendEmail(String fromName, String fromEmail,
                            String toName, String toEmail,
                            String subject, String plainText, String htmlContent) {
        Email email = new Email();

        email.setFrom(fromName, fromEmail);
        email.addRecipient(toName, toEmail);
        email.setSubject(subject);
        email.setPlain(plainText);
        email.setHtml(htmlContent);

        try {
            MailerSendResponse response = mailerSend.emails().send(email);
            return response.messageId;
        } catch (MailerSendException e) {
            e.printStackTrace();
            return null;
        }
    }
}
