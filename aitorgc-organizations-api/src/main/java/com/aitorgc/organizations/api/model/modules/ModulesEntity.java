package com.aitorgc.organizations.api.model.modules;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aitor Gil Callejo
 */
@Entity
@Table(name = "c_modules")
public class ModulesEntity implements Serializable {

	private static final long serialVersionUID = 3513413898706318275L;

	@NotNull
	@Id
	@Basic(optional = false)
	@Column(name = "organization_id", nullable = false, length = 36)
	private String organizationId;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ExternalSynchronizationTypes externalSynchronization;

	@NotNull
	@Column(nullable = false)
	private Boolean employeeSearch;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PresenceValidationTypes presenceValidation;

	@NotNull
	@Column(nullable = false)
	private Boolean suggestions;

	@NotNull
	@Column(nullable = false)
	private Boolean findSlot;

	@NotNull
	@Column(nullable = false)
	private Boolean vehiclesRequired;

	@NotNull
	@Column(nullable = false)
	private Boolean usersCanEditWorkingStatus;

	@NotNull
	@Column(nullable = false)
	private Boolean webAppCheckInWithoutCamera;

	@NotNull
	@Column(nullable = false)
	private Boolean syncUsersByFile;

	@NotNull
	@Column(nullable = false)
	private Boolean allowExternalBookings;

	@NotNull
	@Column(nullable = false)
	private Boolean selfRegistration;

	@NotNull
	@Column(nullable = false)
	private Boolean allowMicrosoftGroupSync;

	@NotNull
	@Column(nullable = false)
	private Boolean searchEmployeeMandatory;

	@NotNull
	@Column(nullable = false)
	private Boolean automaticVehicleValidation;

	@NotNull
	@Column(nullable = false)
	private Boolean enableMicrosoftTeamsSync;

	@NotNull
	@Column(nullable = false)
	private Boolean enableMicrosoftUsersSync;

	@NotNull
	@Column(name = "show_building_access", nullable = false)
	private Boolean showBuildingAccess;

	protected ModulesEntity() {
		super();
	}

	public ModulesEntity(@NotNull String organizationId, @NotNull ExternalSynchronizationTypes externalSynchronization,
			@NotNull Boolean employeeSearch, @NotNull PresenceValidationTypes presenceValidation,
			@NotNull Boolean suggestions, @NotNull Boolean findSlot, @NotNull Boolean vehiclesRequired,
			@NotNull Boolean usersCanEditWorkingStatus, @NotNull Boolean webAppCheckInWithoutCamera,
			@NotNull Boolean syncUsersByFile, @NotNull Boolean allowExternalBookings, @NotNull Boolean selfRegistration,
			@NotNull Boolean allowMicrosoftGroupSync, @NotNull Boolean searchEmployeeMandatory,
			@NotNull Boolean automaticVehicleValidation, @NotNull Boolean enableMicrosoftTeamsSync,
			@NotNull Boolean enableMicrosoftUsersSync, @NotNull Boolean showBuildingAccess) {
		super();
		this.organizationId = organizationId;
		this.externalSynchronization = externalSynchronization;
		this.employeeSearch = employeeSearch;
		this.presenceValidation = presenceValidation;
		this.suggestions = suggestions;
		this.findSlot = findSlot;
		this.vehiclesRequired = vehiclesRequired;
		this.usersCanEditWorkingStatus = usersCanEditWorkingStatus;
		this.webAppCheckInWithoutCamera = webAppCheckInWithoutCamera;
		this.syncUsersByFile = syncUsersByFile;
		this.allowExternalBookings = allowExternalBookings;
		this.selfRegistration = selfRegistration;
		this.allowMicrosoftGroupSync = allowMicrosoftGroupSync;
		this.searchEmployeeMandatory = searchEmployeeMandatory;
		this.automaticVehicleValidation = automaticVehicleValidation;
		this.enableMicrosoftTeamsSync = enableMicrosoftTeamsSync;
		this.enableMicrosoftUsersSync = enableMicrosoftUsersSync;
		this.showBuildingAccess = showBuildingAccess;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public ExternalSynchronizationTypes getExternalSynchronization() {
		return externalSynchronization;
	}

	public void setExternalSynchronization(ExternalSynchronizationTypes externalSynchronization) {
		this.externalSynchronization = externalSynchronization;
	}

	public Boolean getEmployeeSearch() {
		return employeeSearch;
	}

	public void setEmployeeSearch(Boolean employeeSearch) {
		this.employeeSearch = employeeSearch;
	}

	public PresenceValidationTypes getPresenceValidation() {
		return presenceValidation;
	}

	public void setPresenceValidation(PresenceValidationTypes presenceValidation) {
		this.presenceValidation = presenceValidation;
	}

	public Boolean getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(Boolean suggestions) {
		this.suggestions = suggestions;
	}

	public Boolean getFindSlot() {
		return findSlot;
	}

	public void setFindSlot(Boolean findSlot) {
		this.findSlot = findSlot;
	}

	public Boolean getVehiclesRequired() {
		return vehiclesRequired;
	}

	public void setVehiclesRequired(Boolean vehiclesRequired) {
		this.vehiclesRequired = vehiclesRequired;
	}

	public Boolean getUsersCanEditWorkingStatus() {
		return usersCanEditWorkingStatus;
	}

	public void setUsersCanEditWorkingStatus(Boolean usersCanEditWorkingStatus) {
		this.usersCanEditWorkingStatus = usersCanEditWorkingStatus;
	}

	public Boolean getWebAppCheckInWithoutCamera() {
		return webAppCheckInWithoutCamera;
	}

	public void setWebAppCheckInWithoutCamera(Boolean webAppCheckInWithoutCamera) {
		this.webAppCheckInWithoutCamera = webAppCheckInWithoutCamera;
	}

	public Boolean getSyncUsersByFile() {
		return syncUsersByFile;
	}

	public void setSyncUsersByFile(Boolean syncUsersByFile) {
		this.syncUsersByFile = syncUsersByFile;
	}

	public Boolean getAllowExternalBookings() {
		return allowExternalBookings;
	}

	public void setAllowExternalBookings(Boolean allowExternalBookings) {
		this.allowExternalBookings = allowExternalBookings;
	}

	public Boolean getSelfRegistration() {
		return selfRegistration;
	}

	public void setSelfRegistration(Boolean selfRegistration) {
		this.selfRegistration = selfRegistration;
	}

	public Boolean getAllowMicrosoftGroupSync() {
		return allowMicrosoftGroupSync;
	}

	public void setAllowMicrosoftGroupSync(Boolean allowMicrosoftGroupSync) {
		this.allowMicrosoftGroupSync = allowMicrosoftGroupSync;
	}

	public Boolean getSearchEmployeeMandatory() {
		return searchEmployeeMandatory;
	}

	public void setSearchEmployeeMandatory(Boolean searchEmployeeMandatory) {
		this.searchEmployeeMandatory = searchEmployeeMandatory;
	}

	public Boolean getAutomaticVehicleValidation() {
		return automaticVehicleValidation;
	}

	public void setAutomaticVehicleValidation(Boolean automaticVehicleValidation) {
		this.automaticVehicleValidation = automaticVehicleValidation;
	}

	public Boolean getEnableMicrosoftTeamsSync() {
		return enableMicrosoftTeamsSync;
	}

	public void setEnableMicrosoftTeamsSync(Boolean enableMicrosoftTeamsSync) {
		this.enableMicrosoftTeamsSync = enableMicrosoftTeamsSync;
	}

	public Boolean getEnableMicrosoftUsersSync() {
		return enableMicrosoftUsersSync;
	}

	public void setEnableMicrosoftUsersSync(Boolean enableMicrosoftUsersSync) {
		this.enableMicrosoftUsersSync = enableMicrosoftUsersSync;
	}

	public Boolean getShowBuildingAccess() {
		return showBuildingAccess;
	}

	public void setShowBuildingAccess(Boolean showBuildingAccess) {
		this.showBuildingAccess = showBuildingAccess;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allowExternalBookings, allowMicrosoftGroupSync, automaticVehicleValidation, employeeSearch,
				enableMicrosoftTeamsSync, enableMicrosoftUsersSync, externalSynchronization, findSlot, organizationId,
				presenceValidation, searchEmployeeMandatory, selfRegistration, showBuildingAccess, suggestions,
				syncUsersByFile, usersCanEditWorkingStatus, vehiclesRequired, webAppCheckInWithoutCamera);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModulesEntity other = (ModulesEntity) obj;
		return Objects.equals(allowExternalBookings, other.allowExternalBookings)
				&& Objects.equals(allowMicrosoftGroupSync, other.allowMicrosoftGroupSync)
				&& Objects.equals(automaticVehicleValidation, other.automaticVehicleValidation)
				&& Objects.equals(employeeSearch, other.employeeSearch)
				&& Objects.equals(enableMicrosoftTeamsSync, other.enableMicrosoftTeamsSync)
				&& Objects.equals(enableMicrosoftUsersSync, other.enableMicrosoftUsersSync)
				&& externalSynchronization == other.externalSynchronization && Objects.equals(findSlot, other.findSlot)
				&& Objects.equals(organizationId, other.organizationId)
				&& presenceValidation == other.presenceValidation
				&& Objects.equals(searchEmployeeMandatory, other.searchEmployeeMandatory)
				&& Objects.equals(selfRegistration, other.selfRegistration)
				&& Objects.equals(showBuildingAccess, other.showBuildingAccess)
				&& Objects.equals(suggestions, other.suggestions)
				&& Objects.equals(syncUsersByFile, other.syncUsersByFile)
				&& Objects.equals(usersCanEditWorkingStatus, other.usersCanEditWorkingStatus)
				&& Objects.equals(vehiclesRequired, other.vehiclesRequired)
				&& Objects.equals(webAppCheckInWithoutCamera, other.webAppCheckInWithoutCamera);
	}

	@Override
	public String toString() {
		return "ModulesEntity [organizationId=" + organizationId + ", externalSynchronization="
				+ externalSynchronization + ", employeeSearch=" + employeeSearch + ", presenceValidation="
				+ presenceValidation + ", suggestions=" + suggestions + ", findSlot=" + findSlot + ", vehiclesRequired="
				+ vehiclesRequired + ", usersCanEditWorkingStatus=" + usersCanEditWorkingStatus
				+ ", webAppCheckInWithoutCamera=" + webAppCheckInWithoutCamera + ", syncUsersByFile=" + syncUsersByFile
				+ ", allowExternalBookings=" + allowExternalBookings + ", selfRegistration=" + selfRegistration
				+ ", allowMicrosoftGroupSync=" + allowMicrosoftGroupSync + ", searchEmployeeMandatory="
				+ searchEmployeeMandatory + ", automaticVehicleValidation=" + automaticVehicleValidation
				+ ", enableMicrosoftTeamsSync=" + enableMicrosoftTeamsSync + ", enableMicrosoftUsersSync="
				+ enableMicrosoftUsersSync + ", showBuildingAccess=" + showBuildingAccess + "]";
	}

}
