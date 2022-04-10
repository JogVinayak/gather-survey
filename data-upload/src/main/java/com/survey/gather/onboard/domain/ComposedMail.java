package com.survey.gather.onboard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component

public class ComposedMail {
    String from;
    String subject;
    String body;
    String to;
}
