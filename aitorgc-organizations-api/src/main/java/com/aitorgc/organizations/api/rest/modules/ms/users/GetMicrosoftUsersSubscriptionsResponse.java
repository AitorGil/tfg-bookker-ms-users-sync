package com.aitorgc.organizations.api.rest.modules.ms.users;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@Builder
public class GetMicrosoftUsersSubscriptionsResponse implements Serializable {

	private static final long serialVersionUID = 9200681748913593785L;

	private final List<MicrosoftUserSubscriptionView> subscriptions;
}
