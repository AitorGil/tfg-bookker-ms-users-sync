package com.aitorgc.users.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

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
@NoArgsConstructor
@Embeddable
public class VehicleBuildingPK implements Serializable {

	private static final long serialVersionUID = 3513413898706318275L;
	@NotNull
	@NonNull
	@Column(nullable = false, length = 36)
	private String buildingId;

	@NotNull
	@NonNull
	@Column(nullable = false)
	private String licensePlate;
}
