package com.aitorgc.users.api.rest.groups;

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
public class GetUserGroupsResponse implements Serializable {

    private static final long serialVersionUID = -6906770358670341476L;

    private final List<GroupView> groups;
}
