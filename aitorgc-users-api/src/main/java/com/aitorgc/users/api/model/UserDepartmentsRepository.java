package com.aitorgc.users.api.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public interface UserDepartmentsRepository extends JpaRepository<UserDepartmentsEntity, UserDepartmentsPK> {

	List<UserDepartmentsEntity> findAllByUserDepartmentsPKUserId(String userId);

	List<UserDepartmentsEntity> findAllByUserDepartmentsPKDepartmentId(String departmentId);

	void deleteAllByUserDepartmentsPKDepartmentId(String departmentId);

	void deleteAllByUserDepartmentsPKUserIdAndUserDepartmentsPKDepartmentIdNotIn(String userId,
			List<String> departmentsIds);

	void deleteAllByUserDepartmentsPKUserId(String userId);

	boolean existsByUserDepartmentsPKUserIdAndUserDepartmentsPKDepartmentIdNot(String userId, String departmentId);
}
