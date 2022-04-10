package com.survey.gather.services;

import com.survey.gather.onboard.domain.TenantUser;
import com.survey.gather.onboard.repository.TenantUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantUserService {
    @Autowired
    TenantUserRepository repository;

    public TenantUser saveUser (TenantUser user){
        return repository.save(user);
    }
}
