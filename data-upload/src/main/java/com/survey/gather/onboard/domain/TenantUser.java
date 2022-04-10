package com.survey.gather.onboard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
@Component
public class TenantUser {
    @Id
    String tenantId;
    String name;
    String dateOfBirth;
    String email;
    String phone;
    String orgName;
    String address;
}
