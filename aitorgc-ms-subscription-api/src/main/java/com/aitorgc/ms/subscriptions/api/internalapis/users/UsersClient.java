package com.aitorgc.ms.subscriptions.api.internalapis.users;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Aitor Gil Callejo
 */
@FeignClient(name = "usersClient", url = "${bookker.internal.apis.users.url}", configuration = UsersFeignClientConfiguration.class)
public interface UsersClient {

    @PostMapping("/")
    CreateUserResponse createUser(@RequestBody CreateUserRequest request);

    @PostMapping("/find")
    FindUserResponse findUser(@RequestBody FindUserRequest request);

    @PutMapping("/{userId}")
    UpdateUserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserRequest request);

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable("userId") String userId);

    @GetMapping("/rules/organizations/{organizationId}/default")
    public UserRule getDefaultOrganizationUserRule(@PathVariable("organizationId") String organizationId);

    @GetMapping("/groups/organizations/{organizationId}/default")
    public GetDefaultOrganizationGroupsResponse getDefaultOrganizationGroups(
	    @PathVariable("organizationId") String organizationId);

    @GetMapping("/groups/organizations/{organizationId}")
    public GetOrganizationGroupsResponse getOrganizationGroups(@PathVariable("organizationId") String organizationId,
	    @RequestParam(name = "onlySynchronizedGroups", required = false, defaultValue = "false") Boolean onlySynchronizedGroups);
}
