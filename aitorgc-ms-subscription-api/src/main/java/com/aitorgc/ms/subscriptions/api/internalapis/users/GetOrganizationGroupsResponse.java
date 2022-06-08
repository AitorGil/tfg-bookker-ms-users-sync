package com.aitorgc.ms.subscriptions.api.internalapis.users;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@NoArgsConstructor
public class GetOrganizationGroupsResponse implements Serializable {

	private static final long serialVersionUID = -6906770358670341476L;

	private List<Group> groups;
}
