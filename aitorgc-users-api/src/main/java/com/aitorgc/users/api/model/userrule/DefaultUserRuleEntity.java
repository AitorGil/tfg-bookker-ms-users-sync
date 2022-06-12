package com.aitorgc.users.api.model.userrule;

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
 *
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "es_default_user_rule")
public class DefaultUserRuleEntity {

	@NotNull
	@NonNull
	@Id
	@Column(nullable = false, length = 36)
	private String organizationId;

	@NotNull
	@NonNull
	@Column(nullable = false, length = 36)
	private String ruleId;
}
