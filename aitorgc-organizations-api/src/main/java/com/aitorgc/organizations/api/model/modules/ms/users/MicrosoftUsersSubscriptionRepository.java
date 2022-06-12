package com.aitorgc.organizations.api.model.modules.ms.users;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aitor Gil Callejo
 */
@Repository
public interface MicrosoftUsersSubscriptionRepository extends JpaRepository<MicrosoftUsersSubscriptionEntity, String> {

	List<MicrosoftUsersSubscriptionEntity> findAllByOrganizationId(String organizationId, Pageable pageable);

	MicrosoftUsersSubscriptionEntity findByClientState(String clientState);

	boolean existsByIdAndOrganizationId(String id, String organizationId);

	MicrosoftUsersSubscriptionEntity findByIdAndOrganizationId(String id, String organizationId);
}
