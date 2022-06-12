package com.aitorgc.users.api.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public interface UserExtraFieldNameRepository extends JpaRepository<UserExtraFieldNameEntity, String> {

	List<UserExtraFieldNameEntity> findByOrganizationId(OrganizationId organizationId);

	boolean existsByOrganizationIdAndColumnName(OrganizationId organizationId, String columnName);

	void deleteAllByOrganizationId(OrganizationId organizationId);
}
