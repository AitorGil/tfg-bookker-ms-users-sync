package com.aitorgc.users.api.model.groups;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aitor Gil Callejo
 *
 */
public interface DefaultUserGroupRepository extends JpaRepository<DefaultUserGroupEntity, DefaultUserGroupPK> {

    List<DefaultUserGroupEntity> findAllByDefaultUserGroupPKOrganizationId(String organizationId);

}
