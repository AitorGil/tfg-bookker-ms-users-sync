package com.aitorgc.users.api.model.updates;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Aitor Gil Callejo
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "update_user_resources")
public class UpdateUserResourcesEntity {

    @NotNull
    @NonNull
    @Id
    @Column(name = "user_id", nullable = false, length = 36)
    private String userId;

    @NotNull
    @NonNull
    @Column(name = "resources_with_access", nullable = false)
    private String resourcesWithAccess;
}
