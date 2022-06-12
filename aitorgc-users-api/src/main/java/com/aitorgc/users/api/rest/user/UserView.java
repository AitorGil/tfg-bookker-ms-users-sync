package com.aitorgc.users.api.rest.user;

import java.io.Serializable;
import java.util.Date;

import com.aitorgc.users.api.model.UserEntity;
import com.aitorgc.users.api.model.UserStatus;
import com.aitorgc.users.api.model.UserTypes;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserView implements Serializable {

	private static final long serialVersionUID = -695252670483126082L;

	private final String id;
	private final String alias;
	private final String name;
	private final String surname;
	private final String upn;
	private final String email;
	private final String image;
	private final String activationReference;
	private final String phone;
	private final String landLine;
	private final String departmentId;
	private final String userRuleId;
	private final String organizationId;
	private final String webRoleId;
	private final String mobileRoleId;
	private final boolean canBeSearched;
	private final String massiveLoadId;
	private final String timezone;
	private final Date lastLoginDate;
	private final Date lastBookingDate;
	private final UserTypes type;
	private final Date lastModifiedDate;
	private final Date createdDate;
	private final String createdBy;
	private final String lastModifiedBy;
	private final String microsoftId;
	private final String employeeId;
	private final String workingCalendarId;
	private final String language;
	private final String imageId;
	private final boolean acceptedBookkerPolicy;
	private final boolean acceptedCustomPolicy;
	private final Date firedDate;
	private final String dataSource;
	private final Date acceptedBookkerPolicyDate;
	private final Date acceptedCustomPolicyDate;
	private final Date obfuscatedDate;

	private final UserStatus status;

	public UserView(UserEntity dao) {
		this.id = dao.getId();
		this.alias = dao.getAlias();
		this.name = dao.getName();
		this.surname = dao.getSurname();
		this.upn = dao.getUpn();
		this.email = dao.getEmail();
		this.image = dao.getImage();
		this.activationReference = dao.getActivationReference();
		this.phone = dao.getPhone();
		this.landLine = dao.getLandLine();
		this.departmentId = dao.getDepartmentId();
		this.userRuleId = dao.getUserRuleId();
		this.organizationId = dao.getOrganizationId();
		this.webRoleId = dao.getWebRoleId();
		this.mobileRoleId = dao.getMobileRoleId();
		this.canBeSearched = Boolean.TRUE.equals(dao.getCanBeSearched());
		this.massiveLoadId = dao.getMassiveLoadId();
		this.timezone = dao.getTimezone();
		this.lastLoginDate = dao.getLastLoginDate();
		this.lastBookingDate = dao.getLastBookingDate();
		this.type = dao.getType();
		this.lastModifiedDate = dao.getLastModifiedDate();
		this.createdDate = dao.getCreatedDate();
		this.createdBy = dao.getCreatedBy();
		this.lastModifiedBy = dao.getLastModifiedBy();
		this.microsoftId = dao.getMicrosoftId();
		this.employeeId = dao.getEmployeeId();
		this.workingCalendarId = dao.getWorkingCalendarId();
		this.language = dao.getUserLanguage();
		this.imageId = dao.getImageId();
		this.acceptedBookkerPolicy = Boolean.TRUE.equals(dao.getAcceptedBookkerPolicy());
		this.acceptedCustomPolicy = Boolean.TRUE.equals(dao.getAcceptedCustomPolicy());
		this.firedDate = dao.getFiredDate();
		this.dataSource = dao.getDataSource();
		this.acceptedBookkerPolicyDate = dao.getAcceptedBookkerPolicyDate();
		this.acceptedCustomPolicyDate = dao.getAcceptedCustomPolicyDate();
		this.obfuscatedDate = dao.getObfuscatedDate();
		this.status = dao.getStatus();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getAlias() {
		return alias;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getUpn() {
		return upn;
	}

	public String getEmail() {
		return email;
	}

	public String getImage() {
		return image;
	}

	public String getActivationReference() {
		return activationReference;
	}

	public String getPhone() {
		return phone;
	}

	public String getLandLine() {
		return landLine;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public String getUserRuleId() {
		return userRuleId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public String getWebRoleId() {
		return webRoleId;
	}

	public String getMobileRoleId() {
		return mobileRoleId;
	}

	public boolean isCanBeSearched() {
		return canBeSearched;
	}

	public String getMassiveLoadId() {
		return massiveLoadId;
	}

	public String getTimezone() {
		return timezone;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public Date getLastBookingDate() {
		return lastBookingDate;
	}

	public UserTypes getType() {
		return type;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public String getMicrosoftId() {
		return microsoftId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getWorkingCalendarId() {
		return workingCalendarId;
	}

	public String getLanguage() {
		return language;
	}

	public String getImageId() {
		return imageId;
	}

	public boolean isAcceptedBookkerPolicy() {
		return acceptedBookkerPolicy;
	}

	public boolean isAcceptedCustomPolicy() {
		return acceptedCustomPolicy;
	}

	public Date getFiredDate() {
		return firedDate;
	}

	public String getDataSource() {
		return dataSource;
	}

	public Date getAcceptedBookkerPolicyDate() {
		return acceptedBookkerPolicyDate;
	}

	public Date getAcceptedCustomPolicyDate() {
		return acceptedCustomPolicyDate;
	}

	public Date getObfuscatedDate() {
		return obfuscatedDate;
	}

	public UserStatus getStatus() {
		return status;
	}

}
