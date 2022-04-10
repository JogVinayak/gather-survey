package com.survey.gather.onboard.resource;

import com.survey.gather.onboard.domain.TenantUser;
import com.survey.gather.services.TenantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnboardController {
    @Autowired
    TenantUserService service;

    @PostMapping("/onboard")
    public ResponseEntity<TenantUser> onboardTenantUser(@RequestBody TenantUser tenantUser){
        return new ResponseEntity<TenantUser>(service.saveUser(tenantUser), HttpStatus.OK);
    }
}
