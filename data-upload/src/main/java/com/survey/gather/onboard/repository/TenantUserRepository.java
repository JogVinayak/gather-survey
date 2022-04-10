package com.survey.gather.onboard.repository;

import com.survey.gather.onboard.domain.TenantUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface TenantUserRepository extends MongoRepository<TenantUser, String> {
}
