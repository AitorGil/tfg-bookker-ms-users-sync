package com.aitorgc.ms.subscriptions.internalapis.organizations;

import java.io.Serializable;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMicrosoftUsersSubscriptionsResponse implements Serializable {

	private static final long serialVersionUID = 9200681748913593785L;

	private List<MicrosoftUserSubscription> subscriptions;
}
