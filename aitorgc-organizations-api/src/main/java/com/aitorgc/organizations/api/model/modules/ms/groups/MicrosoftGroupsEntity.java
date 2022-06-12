package com.aitorgc.organizations.api.model.modules.ms.groups;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aitor Gil Callejo
 */
@Entity
@Table(name = "mod_microsoft_groups")
public class MicrosoftGroupsEntity implements Serializable {

    private static final long serialVersionUID = -8073100910984751477L;

    @NotNull
    @Id
    @Column(name = "organization_id", nullable = false, length = 36)
    private String organizationId;

    @NotNull
    @Column(name = "tenant_id", nullable = false, length = 100)
    private String tenantId;

    @NotNull
    @Column(name = "delegated_permissions", nullable = false)
    private Boolean delegatedPermissions;

    @Column(name = "application_id", length = 100)
    private String applicationId;

    @Column(name = "application_secret", length = 100)
    private String applicationSecret;

    protected MicrosoftGroupsEntity() {
	super();
    }

    public MicrosoftGroupsEntity(@NotNull String organizationId, @NotNull String tenantId,
	    @NotNull Boolean delegatedPermissions, String applicationId, String applicationSecret) {
	super();
	this.organizationId = organizationId;
	this.tenantId = tenantId;
	this.delegatedPermissions = delegatedPermissions;
	this.applicationId = applicationId;
	this.applicationSecret = applicationSecret;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public String getOrganizationId() {
	return organizationId;
    }

    public void setOrganizationId(String organizationId) {
	this.organizationId = organizationId;
    }

    public String getTenantId() {
	return tenantId;
    }

    public void setTenantId(String tenantId) {
	this.tenantId = tenantId;
    }

    public Boolean getDelegatedPermissions() {
	return delegatedPermissions;
    }

    public void setDelegatedPermissions(Boolean delegatedPermissions) {
	this.delegatedPermissions = delegatedPermissions;
    }

    public String getApplicationId() {
	return applicationId;
    }

    public void setApplicationId(String applicationId) {
	this.applicationId = applicationId;
    }

    public String getApplicationSecret() {
	return applicationSecret;
    }

    public void setApplicationSecret(String applicationSecret) {
	this.applicationSecret = applicationSecret;
    }

    @Override
    public int hashCode() {
	return Objects.hash(applicationId, applicationSecret, delegatedPermissions, organizationId, tenantId);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	MicrosoftGroupsEntity other = (MicrosoftGroupsEntity) obj;
	return Objects.equals(applicationId, other.applicationId)
		&& Objects.equals(applicationSecret, other.applicationSecret)
		&& Objects.equals(delegatedPermissions, other.delegatedPermissions)
		&& Objects.equals(organizationId, other.organizationId) && Objects.equals(tenantId, other.tenantId);
    }

    @Override
    public String toString() {
	return "MicrosoftGroupsEntity [organizationId=" + organizationId + ", tenantId=" + tenantId
		+ ", delegatedPermissions=" + delegatedPermissions + ", applicationId=" + applicationId
		+ ", applicationSecret=" + applicationSecret + "]";
    }

}
