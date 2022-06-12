package com.aitorgc.users.api.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public interface UserGroupsRepository extends JpaRepository<UserGroupsEntity, UserGroupsPK> {

	public List<UserGroupsEntity> findAllByUserGroupsPKUserId(String userGroupsPKUserId);

	public List<UserGroupsEntity> findAllByUserGroupsPKGroupId(String userGroupsPKGroupId);

	void deleteAllByUserGroupsPKUserId(String userId);

	boolean existsByUserGroupsPKGroupId(String groupId);

	boolean existsByUserGroupsPKUserId(String userId);

	int countByUserGroupsPKGroupId(String userGroupsPKGroupId);

	boolean existsByUserGroupsPKGroupIdAndUserGroupsPKUserId(String groupId, String userId);
}
