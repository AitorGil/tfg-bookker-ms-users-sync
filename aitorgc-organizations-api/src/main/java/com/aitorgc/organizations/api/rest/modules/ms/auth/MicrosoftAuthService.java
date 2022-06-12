package com.aitorgc.organizations.api.rest.modules.ms.auth;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aitorgc.organizations.api.model.OrganizationRepository;
import com.aitorgc.organizations.api.model.modules.ms.auth.MicrosoftAuthEntity;
import com.aitorgc.organizations.api.model.modules.ms.auth.MicrosoftAuthRepository;
import com.aitorgc.organizations.api.model.modules.ms.auth.OfficeLocationEntity;
import com.aitorgc.organizations.api.model.modules.ms.auth.OfficeLocationsRepository;
import com.aitorgc.organizations.api.rest.error.MicrosoftAuthNotFoundException;
import com.aitorgc.organizations.api.rest.error.OrganizationNotFoundException;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Service
@RequiredArgsConstructor
public class MicrosoftAuthService {
	private static final String ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY = "organizationId can not be null or empty";

	private final MicrosoftAuthRepository microsoftAuthRepository;
	private final OfficeLocationsRepository officeLocationsRepository;
	private final OrganizationRepository organizationRepository;

	@Transactional(readOnly = true)
	public MicrosoftAuthView getMicrosoftAuthConfig(final String organizationId, final boolean withOfficeLocations) {
		if (Strings.isBlank(organizationId)) {
			throw new IllegalArgumentException(ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		if (!organizationRepository.existsById(organizationId)) {
			throw new OrganizationNotFoundException(organizationId);
		}

		final MicrosoftAuthEntity microsoftAuthEntity = findMicrosoftAuthEntity(organizationId);

		if (Objects.isNull(microsoftAuthEntity)) {
			throw new MicrosoftAuthNotFoundException(organizationId);
		}

		List<OfficeLocationView> officeLocations = null;
		if (withOfficeLocations) {
			officeLocations = findOfficeLocations(organizationId).stream().map(this::constructOfficeLocationView)
					.collect(Collectors.toList());
		}

		return constructMicrosoftAuthView(microsoftAuthEntity, officeLocations);
	}

	@Transactional(readOnly = true)
	public List<OfficeLocationView> getOfficeLocations(final String organizationId) {
		if (Strings.isBlank(organizationId)) {
			throw new IllegalArgumentException(ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		if (!organizationRepository.existsById(organizationId)) {
			throw new OrganizationNotFoundException(organizationId);
		}

		return findOfficeLocations(organizationId).stream().map(this::constructOfficeLocationView)
				.collect(Collectors.toList());
	}

	private MicrosoftAuthEntity findMicrosoftAuthEntity(final String organizationId) {
		return microsoftAuthRepository.findById(organizationId).orElse(null);
	}

	private List<OfficeLocationEntity> findOfficeLocations(final String organizationId) {
		return officeLocationsRepository.findAllByOrganizationId(organizationId);
	}

	private OfficeLocationView constructOfficeLocationView(OfficeLocationEntity entity) {
		return OfficeLocationView.builder().id(entity.getId()).tenantId(entity.getTenantId())
				.officeLocation(entity.getOfficeLocation()).organizationId(entity.getOrganizationId()).build();
	}

	private MicrosoftAuthView constructMicrosoftAuthView(MicrosoftAuthEntity entity,
			final List<OfficeLocationView> officeLocations) {
		return MicrosoftAuthView.builder().organizationId(entity.getOrganizationId()).tenantId(entity.getTenantId())
				.delegatedPermissions(Boolean.TRUE.equals(entity.getDelegatedPermissions()))
				.applicationId(entity.getApplicationId()).applicationSecret(entity.getApplicationSecret())
				.enableEmployeeId(Boolean.TRUE.equals(entity.getEnableEmployeeId()))
				.enableOfficeLocationsFilter(Boolean.TRUE.equals(entity.getEnableOfficeLocationsFilter()))
				.officeLocations(officeLocations).build();
	}
}
