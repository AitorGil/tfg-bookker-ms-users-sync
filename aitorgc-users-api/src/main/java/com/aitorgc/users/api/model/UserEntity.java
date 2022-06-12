package com.aitorgc.users.api.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Aitor Gil Callejo
 */
@Entity
@Table(name = "c_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "alias" }),
		@UniqueConstraint(columnNames = { "activation_reference" }), @UniqueConstraint(columnNames = { "upn" }),
		@UniqueConstraint(columnNames = { "email" }), @UniqueConstraint(columnNames = { "microsoft_id" })

})
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = 3513413898706318275L;

	@Column(nullable = false, length = 255)
	@NotEmpty(message = "* Name can't be empty")
	private String name;

	@Column(nullable = false, length = 255)
	@NotEmpty(message = "* Surname can't be empty")
	private String surname;

	@Column(name = "alias", nullable = false, length = 255)
	@NotEmpty(message = "* Alias can't be empty")
	private String alias;

	@Column(name = "upn", nullable = false, length = 255)
	@Email(message = "UPN should be a valid email")
	@NotNull
	private String upn;

	@Column(name = "email", nullable = false, length = 255)
	@Email(message = "Email should be valid")
	@NotNull
	private String email;

	@Size(max = 255)
	@Column(length = 255)
	private String phone;

	@Size(max = 255)
	@Column(length = 255)
	private String landLine;

	@Size(max = 255)
	@Column(length = 255)
	private String image;

	/**
	 * Imagen de perfil del usuario (identificador único)
	 */
	@Size(max = 255)
	@Column(length = 255)
	private String imageId;

	@Size(max = 255)
	@NotNull
	@Column(nullable = false, length = 255)
	private String password;

	@Size(max = 36)
	@Column(name = "activation_reference", length = 36)
	private String activationReference;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserStatus status;

	@NotNull
	@Column(nullable = false)
	private Boolean canBeSearched;

	@NotNull
	@Size(max = 36)
	@Column(nullable = false, length = 36)
	private String organizationId;

	@Size(max = 36)
	@Column(length = 36)
	private String departmentId;

	@Size(max = 36)
	@Column(length = 36)
	private String userRuleId;

	@Column(nullable = true, length = 36)
	private String webRoleId;

	@Column(nullable = true, length = 36)
	private String mobileRoleId;

	@Column(name = "massive_load_id", nullable = true, length = 36)
	private String massiveLoadId;

	@Column(name = "timezone", length = 255)
	private String timezone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date lastLoginDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date lastBookingDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserTypes type;

	@Column(name = "working_calendar_id")
	private String workingCalendarId;

	@Column(name = "microsoft_id", nullable = true, length = 255)
	private String microsoftId;

	@Column(name = "employee_id", nullable = true, length = 255)
	private String employeeId;

	@Column(length = 45)
	private String userLanguage;

	@NotNull
	@Column(nullable = false)
	private Boolean acceptedBookkerPolicy;

	@NotNull
	@Column(nullable = false)
	private Boolean acceptedCustomPolicy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date firedDate;

	@Column
	private String dataSource;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date acceptedBookkerPolicyDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date acceptedCustomPolicyDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
    private Date obfuscatedDate;

    @Getter
    @Setter
    @Column(name = "google_id", nullable = true, length = 255)
    private String googleId;

	protected UserEntity() {
	}

	public UserEntity(@NotEmpty(message = "* Name can't be empty") String name,
			@NotEmpty(message = "* Surname can't be empty") String surname,
			@NotEmpty(message = "* Alias can't be empty") String alias,
			@Email(message = "UPN should be a valid email") @NotNull String upn,
			@Email(message = "Email should be valid") @NotNull String email, @Size(max = 255) @NotNull String password,
			@NotNull UserStatus status, @NotNull Boolean canBeSearched, @NotNull @Size(max = 36) String organizationId,
			@NotNull UserTypes type, @NotNull Boolean acceptedBookkerPolicy, @NotNull Boolean acceptedCustomPolicy) {
		super();
		this.name = name;
		this.surname = surname;
		this.alias = alias;
		this.upn = upn;
		this.email = email;
		this.password = password;
		this.status = status;
		this.canBeSearched = canBeSearched;
		this.organizationId = organizationId;
		this.type = type;
		this.acceptedBookkerPolicy = acceptedBookkerPolicy;
		this.acceptedCustomPolicy = acceptedCustomPolicy;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActivationReference() {
		return activationReference;
	}

	public void setActivationReference(String activationReference) {
		this.activationReference = activationReference;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Boolean getCanBeSearched() {
		return canBeSearched;
	}

	public void setCanBeSearched(Boolean canBeSearched) {
		this.canBeSearched = canBeSearched;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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

	public String getWorkingCalendarId() {
		return workingCalendarId;
	}

	public void setWorkingCalendarId(String workingCalendarId) {
		this.workingCalendarId = workingCalendarId;
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

	public String getUserLanguage() {
		return userLanguage;
	}

	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}

	public Boolean getAcceptedBookkerPolicy() {
		return acceptedBookkerPolicy;
	}

	public void setAcceptedBookkerPolicy(Boolean acceptedBookkerPolicy) {
		this.acceptedBookkerPolicy = acceptedBookkerPolicy;
	}

	public Boolean getAcceptedCustomPolicy() {
		return acceptedCustomPolicy;
	}

	public void setAcceptedCustomPolicy(Boolean acceptedCustomPolicy) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(acceptedBookkerPolicy, acceptedBookkerPolicyDate, acceptedCustomPolicy,
				acceptedCustomPolicyDate, activationReference, alias, canBeSearched, dataSource, departmentId, email,
				employeeId, firedDate, image, imageId, landLine, lastBookingDate, lastLoginDate, massiveLoadId,
				microsoftId, mobileRoleId, name, obfuscatedDate, organizationId, password, phone, status, surname,
				timezone, type, upn, userLanguage, userRuleId, webRoleId, workingCalendarId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(acceptedBookkerPolicy, other.acceptedBookkerPolicy)
				&& Objects.equals(acceptedBookkerPolicyDate, other.acceptedBookkerPolicyDate)
				&& Objects.equals(acceptedCustomPolicy, other.acceptedCustomPolicy)
				&& Objects.equals(acceptedCustomPolicyDate, other.acceptedCustomPolicyDate)
				&& Objects.equals(activationReference, other.activationReference) && Objects.equals(alias, other.alias)
				&& Objects.equals(canBeSearched, other.canBeSearched) && Objects.equals(dataSource, other.dataSource)
				&& Objects.equals(departmentId, other.departmentId) && Objects.equals(email, other.email)
				&& Objects.equals(employeeId, other.employeeId) && Objects.equals(firedDate, other.firedDate)
				&& Objects.equals(image, other.image) && Objects.equals(imageId, other.imageId)
				&& Objects.equals(landLine, other.landLine) && Objects.equals(lastBookingDate, other.lastBookingDate)
				&& Objects.equals(lastLoginDate, other.lastLoginDate)
				&& Objects.equals(massiveLoadId, other.massiveLoadId) && Objects.equals(microsoftId, other.microsoftId)
				&& Objects.equals(mobileRoleId, other.mobileRoleId) && Objects.equals(name, other.name)
				&& Objects.equals(obfuscatedDate, other.obfuscatedDate)
				&& Objects.equals(organizationId, other.organizationId) && Objects.equals(password, other.password)
				&& Objects.equals(phone, other.phone) && status == other.status
				&& Objects.equals(surname, other.surname) && Objects.equals(timezone, other.timezone)
				&& type == other.type && Objects.equals(upn, other.upn)
				&& Objects.equals(userLanguage, other.userLanguage) && Objects.equals(userRuleId, other.userRuleId)
				&& Objects.equals(webRoleId, other.webRoleId)
				&& Objects.equals(workingCalendarId, other.workingCalendarId);
	}

	@Override
	public String toString() {
		return "UserDAO [name=" + name + ", surname=" + surname + ", upn=" + upn + ", email=" + email + ", status="
				+ status + ", type=" + type + "]";
	}

}
