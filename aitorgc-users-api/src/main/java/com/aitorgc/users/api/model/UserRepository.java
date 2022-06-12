package com.aitorgc.users.api.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {

	boolean existsByUpn(String upn);

	boolean existsByEmail(String email);

	boolean existsByUpnAndIdNot(String upn, String userId);

	boolean existsByEmailAndIdNot(String upn, String userId);

	UserEntity findByUpn(String upn);

	UserEntity findByMicrosoftId(String microsoftId);

	UserEntity findByEmail(String email);
}
