package com.hjc.controller;

import com.hjc.common.response.ResponseEntity;
import com.hjc.entity.ContactMessage;
import com.hjc.utils.MailUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;

/**
 * 联系表单控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    @Resource
    private JavaMailSender sender;


    @Value("${spring.mail.username}")
    private String username;

    @Resource
    private org.thymeleaf.TemplateEngine templateEngine;


    @PostMapping
    public ResponseEntity<String> submitContact(@RequestBody ContactMessage message) {
        try {
            log.info("收到联系表单提交: {}", message.getName());

            String subject = "New Contact Message: " + message.getSubject();

            HashMap<String, String> content = new HashMap<>();
            content.put("name", message.getName());
            content.put("email", message.getEmail());
            content.put("phone", message.getPhone() != null ? message.getPhone() : "");
            content.put("company", message.getCompany() != null ? message.getCompany() : "");
            content.put("subject", message.getSubject());
            content.put("message", message.getMessage());

            sendHtmlMail("2020885569@qq.com", null, null, subject, content);

            log.info("联系表单邮件发送成功");
            return ResponseEntity.success("Message sent successfully! We will get back to you soon.");
        } catch (Exception e) {
            log.error("联系表单发送失败", e);
            return ResponseEntity.failure(50001, "Failed to send message: " + e.getMessage());
        }
    }
    public String sendHtmlMail(String targetEmail, String ccEmail, String bccEmail,
                               String subject, HashMap<String, String> content) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(username);
        helper.setSubject(subject);
        helper.setTo(targetEmail);
        //抄送,收到邮件用户可以看到其他收件人
        if (ccEmail != null) {
            helper.setCc(ccEmail);
        }
        //密送 收到邮件用户看不到其他收件人
        if (bccEmail != null) {
            helper.setBcc(bccEmail);
        }
        helper.setSentDate(new Date());
        //生成邮件模板上的内容
        Context context = new Context();
        for (String key : content.keySet()) {
            context.setVariable(key, content.get(key));
        }
        String process = templateEngine.process("mail/contactMail.html", context);
        helper.setText(process, true);
        sender.send(mimeMessage);
        log.info("邮件发送成功");
        return "邮件发送成功";
    }
}
