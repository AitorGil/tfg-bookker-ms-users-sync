package com.aitorgc.users.api.internalapis.organizations;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@NoArgsConstructor
public class Modules implements Serializable {

	private static final long serialVersionUID = -3935897955860385330L;

	private String organizationId;
	private ExternalSynchronizationTypes externalSynchronizationTypes;
	private boolean employeeSearch;
	private PresenceValidationTypes presenceValidation;
	private boolean suggestions;
	private boolean findSlot;
	private boolean vehiclesRequired;
	private boolean usersCanEditWorkingStatus;
	private boolean webAppCheckInWithoutCamera;
	private boolean syncUsersByFile;
	private boolean allowExternalBookings;
	private boolean selfRegistration;
	private boolean allowMicrosoftGroupSync;
	private boolean searchEmployeeMandatory;
	private boolean automaticVehicleValidation;
	private boolean enableMicrosoftTeamsSync;
	private boolean enableMicrosoftUsersSync;
	private boolean showBuildingAccess;
}
