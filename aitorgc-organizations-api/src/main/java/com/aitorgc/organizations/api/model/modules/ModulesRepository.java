package com.aitorgc.organizations.api.model.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aitor Gil Callejo
 */
@Repository
public interface ModulesRepository extends JpaRepository<ModulesEntity, String> {

}
