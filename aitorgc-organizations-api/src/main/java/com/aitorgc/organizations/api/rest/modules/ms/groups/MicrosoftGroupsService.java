package com.aitorgc.organizations.api.rest.modules.ms.groups;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aitorgc.organizations.api.model.OrganizationRepository;
import com.aitorgc.organizations.api.model.modules.ms.groups.MicrosoftGroupsEntity;
import com.aitorgc.organizations.api.model.modules.ms.groups.MicrosoftGroupsRepository;
import com.aitorgc.organizations.api.rest.error.MicrosoftGroupsNotFoundException;
import com.aitorgc.organizations.api.rest.error.OrganizationNotFoundException;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Service
@RequiredArgsConstructor
public class MicrosoftGroupsService {
    private static final String ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY = "organizationId can not be null or empty";

    private final MicrosoftGroupsRepository microsoftGroupsRepository;
    private final OrganizationRepository organizationRepository;

    @Transactional(readOnly = true)
    public MicrosoftGroupsView getMicrosoftGroupsConfig(final String organizationId) {
	if (Strings.isBlank(organizationId)) {
	    throw new IllegalArgumentException(ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY);
	}

	if (!organizationRepository.existsById(organizationId)) {
	    throw new OrganizationNotFoundException(organizationId);
	}

	final MicrosoftGroupsEntity microsoftGroupsEntity = microsoftGroupsRepository.findById(organizationId)
		.orElseThrow(() -> new MicrosoftGroupsNotFoundException(organizationId));

	return constructMicrosoftGroupsView(microsoftGroupsEntity);
    }

    private MicrosoftGroupsView constructMicrosoftGroupsView(MicrosoftGroupsEntity entity) {
	return MicrosoftGroupsView.builder().organizationId(entity.getOrganizationId()).tenantId(entity.getTenantId())
		.delegatedPermissions(Boolean.TRUE.equals(entity.getDelegatedPermissions()))
		.applicationId(entity.getApplicationId()).applicationSecret(entity.getApplicationSecret()).build();
    }
}
