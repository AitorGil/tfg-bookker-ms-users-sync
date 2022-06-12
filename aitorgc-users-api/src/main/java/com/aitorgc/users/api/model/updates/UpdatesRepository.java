package com.aitorgc.users.api.model.updates;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aitor Gil Callejo
 */
public interface UpdatesRepository extends JpaRepository<UpdatesEntity, String> {

    UpdatesEntity findByOrganizationId(String organizationId);
}
