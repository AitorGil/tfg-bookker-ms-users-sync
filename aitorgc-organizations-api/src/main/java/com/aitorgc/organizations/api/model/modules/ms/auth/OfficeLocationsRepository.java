package com.aitorgc.organizations.api.model.modules.ms.auth;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aitor Gil Callejo
 */
public interface OfficeLocationsRepository extends JpaRepository<OfficeLocationEntity, String> {

    OfficeLocationEntity findByTenantIdAndOfficeLocation(String tenantId, String officeLocation);

    List<OfficeLocationEntity> findAllByOrganizationId(String organizationId);

    boolean existsByOfficeLocationAndTenantId(String officeLocation, String tenantId);

    long deleteAllByOrganizationId(String organizationId);
}
