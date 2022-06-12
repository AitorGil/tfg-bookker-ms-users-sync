package com.aitorgc.users.api.rest.groups;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aitorgc.users.api.model.UserRepository;
import com.aitorgc.users.api.model.groups.DefaultUserGroupRepository;
import com.aitorgc.users.api.model.groups.GroupEntity;
import com.aitorgc.users.api.model.groups.GroupRepository;
import com.aitorgc.users.api.rest.error.UserNotFoundException;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Service
@RequiredArgsConstructor
public class GroupService {

	private static final String USER_ID_CAN_NOT_BE_NULL_OR_EMPTY = "userId can not be null or empty";
	private static final String ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY = "organizationId can not be null or empty";
	private static final String BUILDING_ID_CAN_NOT_BE_NULL_OR_EMPTY = "buildingId can not be null or empty";

	private final UserRepository userRepository;
	private final GroupRepository groupRepository;
	private final DefaultUserGroupRepository defaultUserGroupRepository;

	@Transactional(readOnly = true)
	public List<GroupView> getUserGroups(String userId) {
		if (Strings.isBlank(userId)) {
			throw new IllegalArgumentException(USER_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		if (!userRepository.existsById(userId)) {
			throw new UserNotFoundException(userId);
		}

		return groupRepository.findAllUserGroupsByUserId(userId).stream().map(this::constructGroupView)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<GroupView> getOrganizationGroups(final String organizationId, final boolean onlySynchronizedGroups) {
		if (Strings.isBlank(organizationId)) {
			throw new IllegalArgumentException(ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		if (onlySynchronizedGroups) {
			return groupRepository.findAllOrganizationSynchronizedGroups(organizationId).stream()
					.filter(g -> !Strings.isBlank(g.getMicrosoftId())).map(this::constructGroupView)
					.collect(Collectors.toList());
		} else {
			return groupRepository.findAllOrganizationGroupsByOrganizationId(organizationId).stream()
					.map(this::constructGroupView).collect(Collectors.toList());
		}
	}

	@Transactional(readOnly = true)
	public List<GroupView> getDefaultOrganizationGroups(String organizationId) {
		if (Strings.isBlank(organizationId)) {
			throw new IllegalArgumentException(ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		final List<String> defaultGroupIdList = defaultUserGroupRepository
				.findAllByDefaultUserGroupPKOrganizationId(organizationId).stream()
				.map(e -> e.getDefaultUserGroupPK().getGroupId()).collect(Collectors.toList());

		if (Objects.isNull(defaultGroupIdList) || defaultGroupIdList.isEmpty()) {
			return new ArrayList<>();
		}

		return groupRepository.findAllById(defaultGroupIdList).stream().map(this::constructGroupView)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<GroupView> getBuildingGroups(String buildingId) {
		if (Strings.isBlank(buildingId)) {
			throw new IllegalArgumentException(BUILDING_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		return groupRepository.findAllByBuildingId(buildingId).stream().map(this::constructGroupView)
				.collect(Collectors.toList());
	}

	private GroupView constructGroupView(GroupEntity dao) {
		return GroupView.builder().id(dao.getId()).name(dao.getName()).description(dao.getDescription())
				.type(dao.getType()).buildingId(dao.getBuildingId()).microsoftId(dao.getMicrosoftId())
				.createdBy(dao.getCreatedBy()).createdDate(dao.getCreatedDate()).lastModifiedBy(dao.getLastModifiedBy())
				.lastModifiedDate(dao.getLastModifiedDate()).build();
	}

}
