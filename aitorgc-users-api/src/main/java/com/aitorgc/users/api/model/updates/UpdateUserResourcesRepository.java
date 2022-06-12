package com.aitorgc.users.api.model.updates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author Aitor Gil Callejo
 */
public interface UpdateUserResourcesRepository extends JpaRepository<UpdateUserResourcesEntity, String> {

    @Modifying
    @Query("delete from UpdateUserResourcesDAO u where u.userId in ?1")
    void deleteWithIds(List<String> ids);
}
