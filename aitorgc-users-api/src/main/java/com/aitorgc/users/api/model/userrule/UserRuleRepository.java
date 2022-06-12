package com.aitorgc.users.api.model.userrule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aitor Gil Callejo
 * 
 */
public interface UserRuleRepository extends JpaRepository<UserRuleEntity, String> {

    List<UserRuleEntity> findAllByOrganizationId(String organizationId);

}
