package com.aitorgc.users.api.rest.util;

import java.util.Date;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aitorgc.users.api.model.updates.UpdateUserResourcesRepository;
import com.aitorgc.users.api.model.updates.UpdatesEntity;
import com.aitorgc.users.api.model.updates.UpdatesRepository;
import com.aitorgc.users.api.rest.error.UpdatesNotFoundException;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Service
@RequiredArgsConstructor
public class UpdatesService {

	private static final String USER_ID_CAN_NOT_BE_NULL_OR_EMPTY = "userId can not be null or empty";

	private final UpdatesRepository updatesRepository;
	private final UpdateUserResourcesRepository updateUserResourcesRepository;

	@Transactional
	public void refreshUpdatesDate(String organizationId, boolean buildings, boolean floors, boolean dictionaries,
			boolean subcateogries, boolean users, boolean panelNotifications, boolean groupsInfo) {
		if (Strings.isBlank(organizationId)) {
			throw new IllegalArgumentException("organizationId can not be null or empty");
		}

		UpdatesEntity updateOrganization = updatesRepository.findById(organizationId)
				.orElseThrow(() -> new UpdatesNotFoundException(organizationId));

		final Date now = new Date();

		if (buildings) {
			updateOrganization.setBuildings(now);
		}

		if (floors) {
			updateOrganization.setFloors(now);
		}

		if (dictionaries) {
			updateOrganization.setDictionaries(now);
		}

		if (subcateogries) {
			updateOrganization.setSubcategories(now);
		}

		if (users) {
			updateOrganization.setUsers(now);
		}

		if (panelNotifications) {
			updateOrganization.setPanelNotifications(now);
		}

		if (groupsInfo) {
			updateOrganization.setGroupsInfo(now);
		}

		if (buildings || floors || dictionaries || subcateogries || users || panelNotifications || groupsInfo) {
			updatesRepository.save(updateOrganization);
		}
	}

	@Transactional
	public void deleteUpdateUserResources(String userId) {
		if (Strings.isBlank(userId)) {
			throw new IllegalArgumentException(USER_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		if (updateUserResourcesRepository.existsById(userId)) {
			updateUserResourcesRepository.deleteById(userId);
		}
	}
}
