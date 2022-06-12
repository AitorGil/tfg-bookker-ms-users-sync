package com.aitorgc.organizations.api.model.modules.ms.auth;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Aitor Gil Callejo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "mod_microsoft_auth_office_locations",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"office_location", "tenant_id"})})
public class OfficeLocationEntity implements Serializable {

    private static final long serialVersionUID = 3513413898706318275L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @NotNull
    @NonNull
    @Column(name = "tenant_id", nullable = false, length = 36)
    private String tenantId;

    @NotNull
    @NonNull
    @Column(name = "office_location", nullable = false, length = 255)
    private String officeLocation;

    @NotNull
    @NonNull
    @Column(name = "organization_id", nullable = false, length = 36)
    private String organizationId;
}
