package com.aitorgc.ms.subscriptions.api.internalapis.users;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
public class GetOrganizationGroupsResponse implements Serializable {

    private static final long serialVersionUID = -6906770358670341476L;

    private final List<Group> groups;
}