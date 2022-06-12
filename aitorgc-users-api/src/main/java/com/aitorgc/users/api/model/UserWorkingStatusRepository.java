package com.aitorgc.users.api.model;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aitor Gil Callejo
 */
public interface UserWorkingStatusRepository extends JpaRepository<UserWorkingStatusEntity, String> {

    Optional<UserWorkingStatusEntity> findByOrganizationIdAndNameIgnoreCase(String organizationId, String name);
}
