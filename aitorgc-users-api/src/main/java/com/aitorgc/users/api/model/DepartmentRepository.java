package com.aitorgc.users.api.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {

	List<DepartmentEntity> findAllByOrganizationId(String organizationId);

	List<DepartmentEntity> findAllByOrganizationIdAndNameIn(String orgId, List<String> names);

	List<DepartmentEntity> findAllByOrganizationIdAndName(String orgId, String name);

}
