package com.tinqinacademy.authenticationHotel.core.service.utils;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
public class EmailService {

    private String emailSender="lachezarradushev9@gmail.com";
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailForAccountActivation(String userFirstName, String toEmail, String randomGeneratedCode) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailSender);
            helper.setTo(toEmail);
            helper.setSubject("Welcome to Our Service, " + userFirstName + "!");
            String htmlMsg = "<html>" +
                             "<body>" +
                             "<h1>Hello, " + userFirstName + "!</h1>" +
                             "<p>Thank you for registering with our service. We're excited to have you on board!</p>" +
                             "<p>To activate your account, please use the following code:</p>" +
                             "<h2>" + randomGeneratedCode + "</h2>" +
                             "<p>If you did not request this email, please ignore it.</p>" +
                             "<p>Best regards,<br/>The Team</p>" +
                             "</body>" +
                             "</html>";
            helper.setText(htmlMsg,true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailException("Invalid credentials");
        }
    }

    public void sendEmailWithNewPassword(String userFirstName, String toEmail, String newRandomGeneratedPassword) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailSender);
            helper.setTo(toEmail);
            helper.setSubject("Your New Password");
            String htmlMsg = "<html>" +
                             "<body>" +
                             "<h1>Hello, " + userFirstName + "!</h1>" +
                             "<p>Your password has been reset as per your request.</p>" +
                             "<p>Here is your new password:</p>" +
                             "<h2 style='color: #2E86C1;'>" + newRandomGeneratedPassword + "</h2>" +
                             "<p>For your security, please change this password immediately after logging in.</p>" +
                             "<p>If you did not request a password reset, please contact our support team immediately.</p>" +
                             "<p>Thank you,<br/>The Support Team</p>" +
                             "</body>" +
                             "</html>";
            helper.setText(htmlMsg, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailException("Unexpected error occurred while sending email with new password.");
        }
    }


}