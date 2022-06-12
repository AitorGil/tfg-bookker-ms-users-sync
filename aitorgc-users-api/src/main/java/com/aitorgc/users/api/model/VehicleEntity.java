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
 * @author Aitor Gil Callejo
 */
@Entity
@Table(name = "p_vehicle")
public class VehicleEntity extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 3513413898706318275L;

	@EmbeddedId
	@NotNull
	protected VehiclePK vehiclePK;

	@NotNull
	@Column(nullable = false)
	private String brand;

	@NotNull
	@Column(nullable = false)
	private String model;

	@NotNull
	@Column(nullable = false)
	private String color;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull
	private VehicleTypes type;

	protected VehicleEntity() {
	}

	public VehicleEntity(@NotNull VehiclePK vehiclePK, @NotNull String brand, @NotNull String model, @NotNull String color,
			@NotNull VehicleTypes type) {
		this.vehiclePK = vehiclePK;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.type = type;
	}

	public VehiclePK getVehiclePK() {
		return vehiclePK;
	}

	public void setVehiclePK(VehiclePK vehiclePK) {
		this.vehiclePK = vehiclePK;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public VehicleTypes getType() {
		return type;
	}

	public void setType(VehicleTypes type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
