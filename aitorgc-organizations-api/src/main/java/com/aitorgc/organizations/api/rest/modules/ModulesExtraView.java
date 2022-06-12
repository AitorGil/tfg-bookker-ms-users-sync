package com.aitorgc.organizations.api.rest.modules;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Value
@Builder
public class ModulesExtraView implements Serializable {

	private static final long serialVersionUID = 5286120826040317211L;

	private final String id;
	private final boolean externalSynchronization;
	private final boolean employeeSearch;
	private final boolean presenceValidation;
	private final boolean findSlot;
	private final boolean statusConfiguration;
	private final boolean groupsSynchro;
	private final boolean spacesSynchro;
	private final boolean teamsSynchro;
	private final boolean usersSynchro;
	private final boolean buildingAccess;
	private final boolean premiumReports;
	private final boolean departments;
	private final boolean bookkerEventPush;

}
