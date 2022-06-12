package com.aitorgc.organizations.api.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Aitor Gil Callejo
 */
@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, String> {

  

}
