package com.aitorgc.users.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Entity
@Table(name = "p_vehicle_building")
public class VehicleBuildingEntity extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 3513413898706318275L;

	@EmbeddedId
	private VehicleBuildingPK vehicleBuildingPK;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull
	private VehicleStatus status;

	protected VehicleBuildingEntity() {
	}

	public VehicleBuildingEntity(VehicleBuildingPK vehicleBuildingPK, @NotNull VehicleStatus status) {
		this.vehicleBuildingPK = vehicleBuildingPK;
		this.status = status;
	}

	public VehicleBuildingPK getVehicleBuildingPK() {
		return vehicleBuildingPK;
	}

	public void setVehicleBuildingPK(VehicleBuildingPK vehicleBuildingPK) {
		this.vehicleBuildingPK = vehicleBuildingPK;
	}

	public VehicleStatus getStatus() {
		return status;
	}

	public void setStatus(VehicleStatus status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
