package com.aitorgc.organizations.api.rest.modules;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public class SubscriptionsListView implements Serializable {

	private static final long serialVersionUID = -3558992062315329871L;

	private final List<SubscriptionView> subscriptions;

	private final String nextToken;

	public SubscriptionsListView(List<SubscriptionView> subscriptions, String nextToken) {
		this.subscriptions = subscriptions;
		this.nextToken = nextToken;
	}

	public List<SubscriptionView> getSubscriptions() {
		return subscriptions;
	}

	public String getNextToken() {
		return nextToken;
	}
}
