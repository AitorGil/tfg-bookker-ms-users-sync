package com.aitorgc.organizations.api.model.modules.ms.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Aitor Gil Callejo
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "mod_microsoft_auth")
public class MicrosoftAuthEntity {

    @NotNull
    @NonNull
    @Id
    @Column(name = "organization_id", nullable = false, length = 36)
    private String organizationId;

    @NotNull
    @NonNull
    @Column(name = "tenant_id", nullable = false, length = 100)
    private String tenantId;

    @Column(name = "delegated_permissions")
    private Boolean delegatedPermissions;

    @Column(name = "application_id", length = 100)
    private String applicationId;

    @Column(name = "application_secret", length = 100)
    private String applicationSecret;

    @Column(name = "enable_employee_id")
    private Boolean enableEmployeeId;

    @Column(name = "enable_office_locations_filter")
    private Boolean enableOfficeLocationsFilter;
}
