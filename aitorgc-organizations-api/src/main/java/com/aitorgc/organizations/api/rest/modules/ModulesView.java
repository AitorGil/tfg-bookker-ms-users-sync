package com.aitorgc.organizations.api.rest.modules;

import java.io.Serializable;

import com.aitorgc.organizations.api.model.modules.ExternalSynchronizationTypes;
import com.aitorgc.organizations.api.model.modules.PresenceValidationTypes;

import lombok.Builder;
import lombok.Value;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Value
@Builder
public class ModulesView implements Serializable {

	private static final long serialVersionUID = -3935897955860385330L;

	private final String organizationId;
	private final ExternalSynchronizationTypes externalSynchronizationTypes;
	private final boolean employeeSearch;
	private final PresenceValidationTypes presenceValidation;
	private final boolean suggestions;
	private final boolean findSlot;
	private final boolean vehiclesRequired;
	private final boolean usersCanEditWorkingStatus;
	private final boolean webAppCheckInWithoutCamera;
	private final boolean syncUsersByFile;
	private final boolean allowExternalBookings;
	private final boolean selfRegistration;
	private final boolean allowMicrosoftGroupSync;
	private final boolean searchEmployeeMandatory;
	private final boolean automaticVehicleValidation;
	private final boolean enableMicrosoftTeamsSync;
	private final boolean enableMicrosoftUsersSync;
	private final boolean showBuildingAccess;
}
