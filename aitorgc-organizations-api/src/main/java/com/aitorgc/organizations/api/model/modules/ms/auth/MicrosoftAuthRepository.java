package com.aitorgc.organizations.api.model.modules.ms.auth;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aitor Gil Callejo
 */
public interface MicrosoftAuthRepository extends JpaRepository<MicrosoftAuthEntity, String> {

    MicrosoftAuthEntity findByOrganizationId(String organizationId);

    List<MicrosoftAuthEntity> findAllByTenantId(String tenantId);
}
