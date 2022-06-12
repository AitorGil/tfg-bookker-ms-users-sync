package com.aitorgc.users.api.model.groups;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Entity
@Table(name = "es_default_user_groups")
public class DefaultUserGroupEntity {

    @NonNull
    @EmbeddedId
    private DefaultUserGroupPK defaultUserGroupPK;

}