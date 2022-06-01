package com.aitorgc.ms.subscriptions.api.internalapis.users;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aitor Gil Callejo
 */
public class User implements Serializable {

	private static final long serialVersionUID = -695252670483126082L;

	private String id;
	private String alias;
	private String name;
	private String surname;
	private String upn;
	private String email;
	private String image;
	private String activationReference;
	private String phone;
	private String landLine;
	private String departmentId;
	private String userRuleId;
	private String organizationId;
	private String webRoleId;
	private String mobileRoleId;
	private String password;
	private boolean canBeSearched;
	private String massiveLoadId;
	private String timezone;
	private Date lastLoginDate;
	private Date lastBookingDate;
	private UserTypes type;
	private Date lastModifiedDate;
	private Date createdDate;
	private String createdBy;
	private String lastModifiedBy;
	private String microsoftId;
	private String employeeId;
	private String workingCalendarId;
	private String language;
	private String imageId;
	private boolean acceptedBookkerPolicy;
	private boolean acceptedCustomPolicy;
	private Date firedDate;
	private String dataSource;
	private Date acceptedBookkerPolicyDate;
	private Date acceptedCustomPolicyDate;
	private Date obfuscatedDate;
	private UserStatus status;

	private List<String> groups;
	private List<String> departments;

	protected User() {

	}

	public User(String name, String surname, String upn, String email, String organizationId, String password) {
		this.name = name;
		this.surname = surname;
		this.upn = upn;
		this.email = email;
		this.organizationId = organizationId;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUpn() {
		return upn;
	}

	public void setUpn(String upn) {
		this.upn = upn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getActivationReference() {
		return activationReference;
	}

	public void setActivationReference(String activationReference) {
		this.activationReference = activationReference;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserRuleId() {
		return userRuleId;
	}

	public void setUserRuleId(String userRuleId) {
		this.userRuleId = userRuleId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getWebRoleId() {
		return webRoleId;
	}

	public void setWebRoleId(String webRoleId) {
		this.webRoleId = webRoleId;
	}

	public String getMobileRoleId() {
		return mobileRoleId;
	}

	public void setMobileRoleId(String mobileRoleId) {
		this.mobileRoleId = mobileRoleId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isCanBeSearched() {
		return canBeSearched;
	}

	public void setCanBeSearched(boolean canBeSearched) {
		this.canBeSearched = canBeSearched;
	}

	public String getMassiveLoadId() {
		return massiveLoadId;
	}

	public void setMassiveLoadId(String massiveLoadId) {
		this.massiveLoadId = massiveLoadId;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getLastBookingDate() {
		return lastBookingDate;
	}

	public void setLastBookingDate(Date lastBookingDate) {
		this.lastBookingDate = lastBookingDate;
	}

	public UserTypes getType() {
		return type;
	}

	public void setType(UserTypes type) {
		this.type = type;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getMicrosoftId() {
		return microsoftId;
	}

	public void setMicrosoftId(String microsoftId) {
		this.microsoftId = microsoftId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getWorkingCalendarId() {
		return workingCalendarId;
	}

	public void setWorkingCalendarId(String workingCalendarId) {
		this.workingCalendarId = workingCalendarId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public boolean isAcceptedBookkerPolicy() {
		return acceptedBookkerPolicy;
	}

	public void setAcceptedBookkerPolicy(boolean acceptedBookkerPolicy) {
		this.acceptedBookkerPolicy = acceptedBookkerPolicy;
	}

	public boolean isAcceptedCustomPolicy() {
		return acceptedCustomPolicy;
	}

	public void setAcceptedCustomPolicy(boolean acceptedCustomPolicy) {
		this.acceptedCustomPolicy = acceptedCustomPolicy;
	}

	public Date getFiredDate() {
		return firedDate;
	}

	public void setFiredDate(Date firedDate) {
		this.firedDate = firedDate;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Date getAcceptedBookkerPolicyDate() {
		return acceptedBookkerPolicyDate;
	}

	public void setAcceptedBookkerPolicyDate(Date acceptedBookkerPolicyDate) {
		this.acceptedBookkerPolicyDate = acceptedBookkerPolicyDate;
	}

	public Date getAcceptedCustomPolicyDate() {
		return acceptedCustomPolicyDate;
	}

	public void setAcceptedCustomPolicyDate(Date acceptedCustomPolicyDate) {
		this.acceptedCustomPolicyDate = acceptedCustomPolicyDate;
	}

	public Date getObfuscatedDate() {
		return obfuscatedDate;
	}

	public void setObfuscatedDate(Date obfuscatedDate) {
		this.obfuscatedDate = obfuscatedDate;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public List<String> getDepartments() {
		return departments;
	}

	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}

}
