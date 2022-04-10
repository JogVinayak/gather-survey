package com.survey.gather.resource;

import com.survey.gather.onboard.domain.ComposedMail;
import com.survey.gather.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InviteController {

    @Autowired
    EmailService emailService;

    @PostMapping("/mail-invite")
    public ResponseEntity<HttpStatus> sendMail(@RequestBody ComposedMail mail){
         emailService.sendSimpleMessage(mail.getTo(), mail.getSubject(), mail.getBody());
         return new ResponseEntity<>(HttpStatus.OK);
    }
}
