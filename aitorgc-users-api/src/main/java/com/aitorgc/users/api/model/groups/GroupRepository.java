package com.aitorgc.users.api.model.groups;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public interface GroupRepository extends JpaRepository<GroupEntity, String> {

	List<GroupEntity> findAllByBuildingId(String buildingId);

	List<GroupEntity> findAllByNameAndBuildingIdIn(String name, List<String> buildingIdList);

	List<GroupEntity> findAllByMicrosoftId(String microsoftId);

	List<GroupEntity> findAllByMicrosoftIdIn(List<String> microsoftId);

	List<GroupEntity> findAllByBuildingIdInAndMicrosoftIdNotNull(List<String> buildingIds);

	List<GroupEntity> findAllByNameInAndBuildingIdIn(List<String> groupNames, List<String> buildingIdList);

	boolean existsByNameAndBuildingId(String name, String buildingId);

	@Query(nativeQuery = true, value = "SELECT * FROM c_group WHERE building_id IN (SELECT id FROM c_building WHERE organization_id = :organizationId)")
	List<GroupEntity> findAllOrganizationGroupsByOrganizationId(@Param("organizationId") String organizationId);

	@Query(nativeQuery = true, value = "SELECT * FROM c_group WHERE building_id IN (SELECT id FROM c_building WHERE organization_id = :organizationId) AND microsoft_id IS NOT NULL")
	List<GroupEntity> findAllOrganizationSynchronizedGroups(@Param("organizationId") String organizationId);

	@Query(nativeQuery = true, value = "SELECT * FROM c_group WHERE id IN (SELECT group_id FROM c_user_groups WHERE user_id = :userId)")
	List<GroupEntity> findAllUserGroupsByUserId(@Param("userId") String userId);

	@Query(nativeQuery = true, value = "select not exists (select * from c_group where id in (:groupIdList) and building_id not in (select id from c_building where organization_id= :organizationId))")
	long groupsAreFromOrganization(@Param("groupIdList") Set<String> groupIdList,
			@Param("organizationId") String organizationId);
}
