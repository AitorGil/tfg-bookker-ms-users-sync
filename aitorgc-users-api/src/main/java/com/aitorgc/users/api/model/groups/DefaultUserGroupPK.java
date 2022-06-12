package com.aitorgc.users.api.model.groups;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class DefaultUserGroupPK implements Serializable {

    private static final long serialVersionUID = 3513413898706318275L;

    @NonNull
    @NotNull
    @Column(nullable = false, length = 36)
    private String organizationId;

    @NonNull
    @NotNull
    @Column(nullable = false, length = 36)
    private String groupId;
}