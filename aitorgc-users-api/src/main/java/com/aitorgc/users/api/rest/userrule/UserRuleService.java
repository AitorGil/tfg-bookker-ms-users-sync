package com.aitorgc.users.api.rest.userrule;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aitorgc.users.api.model.userrule.DefaultUserRuleEntity;
import com.aitorgc.users.api.model.userrule.DefaultUserRuleRepository;
import com.aitorgc.users.api.model.userrule.UserRuleEntity;
import com.aitorgc.users.api.model.userrule.UserRuleRepository;
import com.aitorgc.users.api.rest.error.DefaultUserRuleNotFoundException;
import com.aitorgc.users.api.rest.error.UserRuleNotFoundException;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Service
@RequiredArgsConstructor
public class UserRuleService {

	private static final String ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY = "organizationId can not be null or empty";

	private final UserRuleRepository userRuleRepository;
	private final DefaultUserRuleRepository defaultUserRuleRepository;

	@Transactional(readOnly = true)
	public UserRuleView getDefaultUserRuleByOrganization(String organizationId) {
		if (Strings.isBlank(organizationId)) {
			throw new IllegalArgumentException(ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		final DefaultUserRuleEntity defaultUserRuleEntity = defaultUserRuleRepository.findById(organizationId)
				.orElseThrow(() -> new DefaultUserRuleNotFoundException(organizationId));

		final UserRuleEntity userRuleEntity = userRuleRepository.findById(defaultUserRuleEntity.getRuleId())
				.orElseThrow(() -> new UserRuleNotFoundException(defaultUserRuleEntity.getRuleId()));

		return new UserRuleView(userRuleEntity);
	}

}
