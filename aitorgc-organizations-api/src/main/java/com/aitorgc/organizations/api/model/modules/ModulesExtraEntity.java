package com.aitorgc.organizations.api.model.modules;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Entity
@Table(name = "c_modules_extra")
public class ModulesExtraEntity implements Serializable {

	private static final long serialVersionUID = -1904274273480597569L;

	@NotNull
	@Id
	@Basic(optional = false)
	@Column(name = "organization_id", nullable = false, length = 36)
	private String organizationId;

	@NotNull
	@Column(nullable = false)
	private Boolean externalSynchronization;

	@NotNull
	@Column(nullable = false)
	private Boolean employeeSearch;

	@NotNull
	@Column(nullable = false)
	private Boolean presenceValidation;

	@NotNull
	@Column(nullable = false)
	private Boolean findSlot;

	@NotNull
	@Column(nullable = false)
	private Boolean statusConfiguration;

	@NotNull
	@Column(nullable = false)
	private Boolean groupsSynchro;

	@NotNull
	@Column(nullable = false)
	private Boolean spacesSynchro;

	@NotNull
	@Column(nullable = false)
	private Boolean teamsSynchro;

	@NotNull
	@Column(nullable = false)
	private Boolean usersSynchro;

	@NotNull
	@Column(nullable = false)
	private Boolean buildingAccess;

	@NotNull
	@Column(nullable = false)
	private Boolean premiumReports;

	@NotNull
	@Column(nullable = false)
	private Boolean departments;

	@NotNull
	@Column(nullable = false)
	private Boolean bookkerEventPush;

	protected ModulesExtraEntity() {
		super();
	}

	public ModulesExtraEntity(@NotNull String organizationId, @NotNull Boolean externalSynchronization,
			@NotNull Boolean employeeSearch, @NotNull Boolean presenceValidation, @NotNull Boolean findSlot,
			@NotNull Boolean statusConfiguration, @NotNull Boolean groupsSynchro, @NotNull Boolean spacesSynchro,
			@NotNull Boolean teamsSynchro, @NotNull Boolean usersSynchro, @NotNull Boolean buildingAccess,
			@NotNull Boolean premiumReports, @NotNull Boolean departments, @NotNull Boolean bookkerEventPush) {
		super();
		this.organizationId = organizationId;
		this.externalSynchronization = externalSynchronization;
		this.employeeSearch = employeeSearch;
		this.presenceValidation = presenceValidation;
		this.findSlot = findSlot;
		this.statusConfiguration = statusConfiguration;
		this.groupsSynchro = groupsSynchro;
		this.spacesSynchro = spacesSynchro;
		this.teamsSynchro = teamsSynchro;
		this.usersSynchro = usersSynchro;
		this.buildingAccess = buildingAccess;
		this.premiumReports = premiumReports;
		this.departments = departments;
		this.bookkerEventPush = bookkerEventPush;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Boolean getExternalSynchronization() {
		return externalSynchronization;
	}

	public void setExternalSynchronization(Boolean externalSynchronization) {
		this.externalSynchronization = externalSynchronization;
	}

	public Boolean getEmployeeSearch() {
		return employeeSearch;
	}

	public void setEmployeeSearch(Boolean employeeSearch) {
		this.employeeSearch = employeeSearch;
	}

	public Boolean getPresenceValidation() {
		return presenceValidation;
	}

	public void setPresenceValidation(Boolean presenceValidation) {
		this.presenceValidation = presenceValidation;
	}

	public Boolean getFindSlot() {
		return findSlot;
	}

	public void setFindSlot(Boolean findSlot) {
		this.findSlot = findSlot;
	}

	public Boolean getStatusConfiguration() {
		return statusConfiguration;
	}

	public void setStatusConfiguration(Boolean statusConfiguration) {
		this.statusConfiguration = statusConfiguration;
	}

	public Boolean getGroupsSynchro() {
		return groupsSynchro;
	}

	public void setGroupsSynchro(Boolean groupsSynchro) {
		this.groupsSynchro = groupsSynchro;
	}

	public Boolean getSpacesSynchro() {
		return spacesSynchro;
	}

	public void setSpacesSynchro(Boolean spacesSynchro) {
		this.spacesSynchro = spacesSynchro;
	}

	public Boolean getTeamsSynchro() {
		return teamsSynchro;
	}

	public void setTeamsSynchro(Boolean teamsSynchro) {
		this.teamsSynchro = teamsSynchro;
	}

	public Boolean getUsersSynchro() {
		return usersSynchro;
	}

	public void setUsersSynchro(Boolean usersSynchro) {
		this.usersSynchro = usersSynchro;
	}

	public Boolean getBuildingAccess() {
		return buildingAccess;
	}

	public void setBuildingAccess(Boolean buildingAccess) {
		this.buildingAccess = buildingAccess;
	}

	public Boolean getPremiumReports() {
		return premiumReports;
	}

	public void setPremiumReports(Boolean premiumReports) {
		this.premiumReports = premiumReports;
	}

	public Boolean getDepartments() {
		return departments;
	}

	public void setDepartments(Boolean departments) {
		this.departments = departments;
	}

	public Boolean getBookkerEventPush() {
		return bookkerEventPush;
	}

	public void setBookkerEventPush(Boolean bookkerEventPush) {
		this.bookkerEventPush = bookkerEventPush;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookkerEventPush, buildingAccess, departments, employeeSearch, externalSynchronization,
				findSlot, groupsSynchro, organizationId, premiumReports, presenceValidation, spacesSynchro,
				statusConfiguration, teamsSynchro, usersSynchro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModulesExtraEntity other = (ModulesExtraEntity) obj;
		return Objects.equals(bookkerEventPush, other.bookkerEventPush)
				&& Objects.equals(buildingAccess, other.buildingAccess)
				&& Objects.equals(departments, other.departments)
				&& Objects.equals(employeeSearch, other.employeeSearch)
				&& Objects.equals(externalSynchronization, other.externalSynchronization)
				&& Objects.equals(findSlot, other.findSlot) && Objects.equals(groupsSynchro, other.groupsSynchro)
				&& Objects.equals(organizationId, other.organizationId)
				&& Objects.equals(premiumReports, other.premiumReports)
				&& Objects.equals(presenceValidation, other.presenceValidation)
				&& Objects.equals(spacesSynchro, other.spacesSynchro)
				&& Objects.equals(statusConfiguration, other.statusConfiguration)
				&& Objects.equals(teamsSynchro, other.teamsSynchro) && Objects.equals(usersSynchro, other.usersSynchro);
	}

	@Override
	public String toString() {
		return "ModulesExtraEntity [organizationId=" + organizationId + ", externalSynchronization="
				+ externalSynchronization + ", employeeSearch=" + employeeSearch + ", presenceValidation="
				+ presenceValidation + ", findSlot=" + findSlot + ", statusConfiguration=" + statusConfiguration
				+ ", groupsSynchro=" + groupsSynchro + ", spacesSynchro=" + spacesSynchro + ", teamsSynchro="
				+ teamsSynchro + ", usersSynchro=" + usersSynchro + ", buildingAccess=" + buildingAccess
				+ ", premiumReports=" + premiumReports + ", departments=" + departments + ", bookkerEventPush="
				+ bookkerEventPush + "]";
	}

}
