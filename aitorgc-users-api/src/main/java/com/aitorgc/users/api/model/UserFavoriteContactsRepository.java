package com.aitorgc.users.api.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Aitor Gil Callejo
 * 
 */
public interface UserFavoriteContactsRepository extends JpaRepository<UserFavoriteContactsEntity, UserFavoriteContactsPK> {

    List<UserFavoriteContactsEntity> findAllByUserFavoriteContactsPKUserId(String userId);

    List<UserFavoriteContactsEntity> findAllByUserFavoriteContactsPKContactId(String userId);

    @Modifying
    @Query(nativeQuery = true, value = "delete from user_favorite_contacts where user_id = :userId")
    void deleteAllByUserId(@Param("userId") String userId);

    @Modifying
    @Query(nativeQuery = true, value = "delete from user_favorite_contacts where contact_id = :contactId")
    void deleteAllByContactId(@Param("contactId") String contactId);
}
