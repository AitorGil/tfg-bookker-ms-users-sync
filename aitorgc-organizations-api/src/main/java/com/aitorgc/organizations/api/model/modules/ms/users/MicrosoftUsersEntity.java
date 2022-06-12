package com.aitorgc.organizations.api.model.modules.ms.users;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aitorgc.organizations.api.model.util.Auditable;

/**
 *
 * @author Aitor Gil Callejo
 */
@Entity
@Table(name = "mod_microsoft_users")
public class MicrosoftUsersEntity extends Auditable<String> {

	@Id
	@Column(name = "organization_id", nullable = false, length = 36)
	private String organizationId;

	@Column(name = "tenant_id", nullable = false, length = 100)
	private String tenantId;

	@Column(name = "application_id", nullable = false, length = 100)
	private String applicationId;

	@Column(name = "application_secret", nullable = false, length = 100)
	private String applicationSecret;

	@Temporal(TemporalType.DATE)
	@Column(name = "expiration_date", nullable = false)
	private Date expirationDate;

	protected MicrosoftUsersEntity() {
		super();
	}

	public MicrosoftUsersEntity(String organizationId, String tenantId, String applicationId, String applicationSecret,
			Date expirationDate) {
		super();
		this.organizationId = organizationId;
		this.tenantId = tenantId;
		this.applicationId = applicationId;
		this.applicationSecret = applicationSecret;
		this.expirationDate = expirationDate;
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

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(applicationId, applicationSecret, expirationDate, organizationId, tenantId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MicrosoftUsersEntity other = (MicrosoftUsersEntity) obj;
		return Objects.equals(applicationId, other.applicationId)
				&& Objects.equals(applicationSecret, other.applicationSecret)
				&& Objects.equals(expirationDate, other.expirationDate)
				&& Objects.equals(organizationId, other.organizationId) && Objects.equals(tenantId, other.tenantId);
	}

	@Override
	public String toString() {
		return "MicrosoftUsersEntity [organizationId=" + organizationId + ", tenantId=" + tenantId + ", applicationId="
				+ applicationId + ", applicationSecret=" + applicationSecret + ", expirationDate=" + expirationDate
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy
				+ ", lastModifiedDate=" + lastModifiedDate + "]";
	}

}
