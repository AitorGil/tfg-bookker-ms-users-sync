package com.aitorgc.organizations.api.rest.modules;

import java.io.Serializable;

import software.amazon.awssdk.services.sns.model.Subscription;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public class SubscriptionView implements Serializable {

	private static final long serialVersionUID = -2061361042638818319L;

	private String subscriptionArn;

	private String protocol;

	private String endpoint;

	protected SubscriptionView() {

	}

	public SubscriptionView(Subscription s) {
		this.subscriptionArn = s.subscriptionArn();
		this.protocol = s.protocol();
		this.endpoint = s.endpoint();
	}

	public String getSubscriptionArn() {
		return subscriptionArn;
	}

	public void setSubscriptionArn(String subscriptionArn) {
		this.subscriptionArn = subscriptionArn;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
