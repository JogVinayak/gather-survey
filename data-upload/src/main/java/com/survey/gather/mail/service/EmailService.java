package com.survey.gather.mail.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;



/**
 * Created by Olga on 8/22/2016.
 */
@Component
public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);
    void sendSimpleMessageUsingTemplate(String to,
                                        String subject,
                                        String ...templateModel);
    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);
    
    void sendMessageUsingThymeleafTemplate(String to,
                                           String subject,
                                           Map<String, Object> templateModel) 
            throws IOException;


}