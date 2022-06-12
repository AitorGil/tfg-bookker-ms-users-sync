package com.aitorgc.users.api.rest.user;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.aitorgc.users.api.model.UserStatus;
import com.aitorgc.users.api.model.UserTypes;

import lombok.ToString;

/**
 *
 * @author Aitor Gil Callejo
 */
@ToString
public class UpdateUserRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "user can not be null")
    private User user;

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @ToString
    static class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(max = 255)
	private String name;

	@Size(max = 255)
	private String surname;

	@Size(max = 255)
	@Email(message = "email is not valid")
	private String email;

	@Size(max = 255)
	@Email(message = "upn is not valid")
	private String upn;

	@Size(max = 255)
	private String phone;

	@Size(max = 255)
	private String landLine;

	private UserStatus status;

	private UserTypes type;

	private Boolean canBeSearched;

	@Size(max = 36)
	private String departmentId;

	@Size(max = 36)
	private String userRuleId;

	@Size(max = 36)
	private String webRoleId;

	@Size(max = 36)
	private String mobileRoleId;

	@Size(max = 255)
	private String password;

	private List<String> groups;

	private String microsoftId;

	private String employeeId;

	@Size(max = 36)
	private String workingCalendarId;

	private List<String> departments;

	private String dataSource;

	private Boolean updateLastLoginDate;

	protected User() {
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

	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public String getUpn() {
	    return upn;
	}

	public void setUpn(String upn) {
	    this.upn = upn;
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

	public UserStatus getStatus() {
	    return status;
	}

	public void setStatus(UserStatus status) {
	    this.status = status;
	}

	public UserTypes getType() {
	    return type;
	}

	public void setType(UserTypes type) {
	    this.type = type;
	}

	public Boolean getCanBeSearched() {
	    return canBeSearched;
	}

	public void setCanBeSearched(Boolean canBeSearched) {
	    this.canBeSearched = canBeSearched;
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

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

	public List<String> getGroups() {
	    return groups;
	}

	public void setGroups(List<String> groups) {
	    this.groups = groups;
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

	public List<String> getDepartments() {
	    return departments;
	}

	public void setDepartments(List<String> departments) {
	    this.departments = departments;
	}

	public String getDataSource() {
	    return dataSource;
	}

	public void setDataSource(String dataSource) {
	    this.dataSource = dataSource;
	}

	public Boolean getUpdateLastLoginDate() {
	    return updateLastLoginDate;
	}

	public void setUpdateLastLoginDate(Boolean updateLastLoginDate) {
	    this.updateLastLoginDate = updateLastLoginDate;
	}

    }
}
