package com.aitorgc.organizations.api.rest.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aitorgc.organizations.api.model.modules.ModulesEntity;
import com.aitorgc.organizations.api.model.modules.ModulesExtraEntity;
import com.aitorgc.organizations.api.model.modules.ModulesExtraRepository;
import com.aitorgc.organizations.api.model.modules.ModulesRepository;
import com.aitorgc.organizations.api.rest.error.ModulesExtraNotFoundException;
import com.aitorgc.organizations.api.rest.error.ModulesNotFoundException;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Service
@RequiredArgsConstructor
public class ModulesService {

	private final ModulesRepository modulesRepository;
	private final ModulesExtraRepository modulesExtraRepository;

	@Transactional(readOnly = true)
	public ModulesExtraView getOrganizationModulesExtra(String organizationId) {

		return constructModulesExtraView(modulesExtraRepository.findById(organizationId)
				.orElseThrow(() -> new ModulesExtraNotFoundException(organizationId)));
	}

	@Transactional(readOnly = true)
	public ModulesView getOrganizationModules(String organizationId) {
		return constructModulesView(modulesRepository.findById(organizationId)
				.orElseThrow(() -> new ModulesNotFoundException(organizationId)));
	}

	private ModulesExtraView constructModulesExtraView(final ModulesExtraEntity dao) {
		return ModulesExtraView.builder().id(dao.getOrganizationId())
				.externalSynchronization(Boolean.TRUE.equals(dao.getExternalSynchronization()))
				.employeeSearch(Boolean.TRUE.equals(dao.getEmployeeSearch()))
				.presenceValidation(Boolean.TRUE.equals(dao.getPresenceValidation()))
				.findSlot(Boolean.TRUE.equals(dao.getFindSlot()))
				.statusConfiguration(Boolean.TRUE.equals(dao.getStatusConfiguration()))
				.groupsSynchro(Boolean.TRUE.equals(dao.getGroupsSynchro()))
				.spacesSynchro(Boolean.TRUE.equals(dao.getSpacesSynchro()))
				.teamsSynchro(Boolean.TRUE.equals(dao.getTeamsSynchro()))
				.usersSynchro(Boolean.TRUE.equals(dao.getUsersSynchro()))
				.buildingAccess(Boolean.TRUE.equals(dao.getBuildingAccess()))
				.premiumReports(Boolean.TRUE.equals(dao.getPremiumReports()))
				.departments(Boolean.TRUE.equals(dao.getDepartments()))
				.bookkerEventPush(Boolean.TRUE.equals(dao.getBookkerEventPush())).build();
	}

	private ModulesView constructModulesView(final ModulesEntity dao) {
		return ModulesView.builder().organizationId(dao.getOrganizationId())
				.externalSynchronizationTypes(dao.getExternalSynchronization())
				.employeeSearch(Boolean.TRUE.equals(dao.getEmployeeSearch()))
				.presenceValidation(dao.getPresenceValidation()).suggestions(Boolean.TRUE.equals(dao.getSuggestions()))
				.findSlot(Boolean.TRUE.equals(dao.getFindSlot()))
				.vehiclesRequired(Boolean.TRUE.equals(dao.getVehiclesRequired()))
				.usersCanEditWorkingStatus(Boolean.TRUE.equals(dao.getUsersCanEditWorkingStatus()))
				.webAppCheckInWithoutCamera(Boolean.TRUE.equals(dao.getWebAppCheckInWithoutCamera()))
				.syncUsersByFile(Boolean.TRUE.equals(dao.getSyncUsersByFile()))
				.allowExternalBookings(Boolean.TRUE.equals(dao.getAllowExternalBookings()))
				.selfRegistration(Boolean.TRUE.equals(dao.getSelfRegistration()))
				.allowMicrosoftGroupSync(Boolean.TRUE.equals(dao.getAllowMicrosoftGroupSync()))
				.searchEmployeeMandatory(Boolean.TRUE.equals(dao.getSearchEmployeeMandatory()))
				.automaticVehicleValidation(Boolean.TRUE.equals(dao.getAutomaticVehicleValidation()))
				.enableMicrosoftTeamsSync(Boolean.TRUE.equals(dao.getEnableMicrosoftTeamsSync()))
				.enableMicrosoftUsersSync(Boolean.TRUE.equals(dao.getEnableMicrosoftUsersSync()))
				.showBuildingAccess(Boolean.TRUE.equals(dao.getShowBuildingAccess())).build();
	}

}
