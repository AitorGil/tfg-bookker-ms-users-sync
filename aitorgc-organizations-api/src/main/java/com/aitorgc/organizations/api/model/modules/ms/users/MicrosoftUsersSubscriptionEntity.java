package com.aitorgc.organizations.api.model.modules.ms.users;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.aitorgc.organizations.api.model.util.Auditable;

/**
 *
 * @author Aitor Gil Callejo
 */
@Entity
@Table(name = "microsoft_users_subscription", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "client_state" }) })
public class MicrosoftUsersSubscriptionEntity extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 2179166052338329319L;

	@Id
	@Column(name = "id", length = 50, nullable = false)
	private String id;

	@Column(name = "application_id", length = 36, nullable = false)
	private String applicationId;

	@Column(name = "change_type", length = 45, nullable = false)
	private String changeType;

	@Column(name = "client_state", length = 45, nullable = false)
	private String clientState;

	@Column(name = "expiration_date", nullable = false)
	private OffsetDateTime expirationDateTime;

	@Column(name = "latest_supported_tls_version", length = 45, nullable = false)
	private String latestSupportedTlsVersion;

	@Column(name = "notification_url", length = 256, nullable = false)
	private String notificationUrl;

	@Column(name = "resource", length = 256, nullable = false)
	private String resource;

	@Column(name = "organization_id", length = 36, nullable = false)
	private String organizationId;

	protected MicrosoftUsersSubscriptionEntity() {
	}

	public MicrosoftUsersSubscriptionEntity(String id, String applicationId, String changeType, String clientState,
			OffsetDateTime expirationDateTime, String latestSupportedTlsVersion, String notificationUrl,
			String resource, String organizationId) {
		super();
		this.id = id;
		this.applicationId = applicationId;
		this.changeType = changeType;
		this.clientState = clientState;
		this.expirationDateTime = expirationDateTime;
		this.latestSupportedTlsVersion = latestSupportedTlsVersion;
		this.notificationUrl = notificationUrl;
		this.resource = resource;
		this.organizationId = organizationId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getClientState() {
		return clientState;
	}

	public void setClientState(String clientState) {
		this.clientState = clientState;
	}

	public OffsetDateTime getExpirationDateTime() {
		return expirationDateTime;
	}

	public void setExpirationDateTime(OffsetDateTime expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}

	public String getLatestSupportedTlsVersion() {
		return latestSupportedTlsVersion;
	}

	public void setLatestSupportedTlsVersion(String latestSupportedTlsVersion) {
		this.latestSupportedTlsVersion = latestSupportedTlsVersion;
	}

	public String getNotificationUrl() {
		return notificationUrl;
	}

	public void setNotificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	@Override
	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(applicationId, changeType, clientState, expirationDateTime, id, latestSupportedTlsVersion,
				notificationUrl, organizationId, resource);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MicrosoftUsersSubscriptionEntity other = (MicrosoftUsersSubscriptionEntity) obj;
		return Objects.equals(applicationId, other.applicationId) && Objects.equals(changeType, other.changeType)
				&& Objects.equals(clientState, other.clientState)
				&& Objects.equals(expirationDateTime, other.expirationDateTime) && Objects.equals(id, other.id)
				&& Objects.equals(latestSupportedTlsVersion, other.latestSupportedTlsVersion)
				&& Objects.equals(notificationUrl, other.notificationUrl)
				&& Objects.equals(organizationId, other.organizationId) && Objects.equals(resource, other.resource);
	}

	@Override
	public String toString() {
		return "MicrosoftUsersSubscriptionEntity [id=" + id + ", applicationId=" + applicationId + ", changeType="
				+ changeType + ", clientState=" + clientState + ", expirationDateTime=" + expirationDateTime
				+ ", latestSupportedTlsVersion=" + latestSupportedTlsVersion + ", notificationUrl=" + notificationUrl
				+ ", resource=" + resource + ", organizationId=" + organizationId + "]";
	}

}
