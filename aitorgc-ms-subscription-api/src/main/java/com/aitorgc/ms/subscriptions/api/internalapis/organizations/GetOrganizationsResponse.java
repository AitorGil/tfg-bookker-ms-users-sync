package com.aitorgc.ms.subscriptions.api.internalapis.organizations;

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
public class GetOrganizationsResponse implements Serializable {

	private static final long serialVersionUID = -679597879511896111L;

	private List<Organization> organizations;
}
