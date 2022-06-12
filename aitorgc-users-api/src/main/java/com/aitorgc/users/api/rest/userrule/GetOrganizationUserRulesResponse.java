package com.aitorgc.users.api.rest.userrule;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public class GetOrganizationUserRulesResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<UserRuleView> userRules;

    public GetOrganizationUserRulesResponse(List<UserRuleView> userRules) {
	super();
	this.userRules = userRules;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public List<UserRuleView> getUserRules() {
	return userRules;
    }

}
