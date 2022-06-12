package com.aitorgc.organizations.api.model.modules.ms.users;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aitor Gil Callejo
 */
public interface MicrosoftUsersRepository extends JpaRepository<MicrosoftUsersEntity, String> {

	MicrosoftUsersEntity findByOrganizationId(String organizationId);
}
