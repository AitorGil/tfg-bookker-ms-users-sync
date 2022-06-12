package com.aitorgc.users.api.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public interface UserExtraFieldRepository extends JpaRepository<UserExtraFieldEntity, String> {

	UserExtraFieldEntity findByUserId(UserId userId);

	void deleteByUserId(UserId userId);

}
