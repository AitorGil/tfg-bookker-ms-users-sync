package com.aitorgc.users.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aitor Gil Callejo
 */
@Entity
@Table(name = "c_user_working_status")
public class UserWorkingStatusEntity extends BaseEntity {

    private static final long serialVersionUID = 3513413898706318275L;

    @Column(nullable = false, length = 36)
    @NotEmpty(message = "* Organization ID can't be empty")
    @NotNull
    private String organizationId;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "* Name can't be empty")
    @NotNull
    private String name;

    @Column(nullable = false, length = 255)
    @NotEmpty(message = "* Description can't be empty")
    @NotNull
    private String description;

    @Column(nullable = false, length = 6)
    @NotEmpty(message = "* Color can't be empty")
    @NotNull
    private String color;

    @Column(name = "must_block_bookings", nullable = false)
    @NotNull
    private Boolean mustBlockBookings;

    @Column(name = "must_cede_parking_slot", nullable = false)
    @NotNull
    private Boolean mustCedeParkingSlot;

    protected UserWorkingStatusEntity() {
    }

    public UserWorkingStatusEntity(String organizationId, String name, String description, String color, Boolean mustBlockBookings, Boolean mustCedeParkingSlot) {
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
        this.color = color;
        this.mustBlockBookings = mustBlockBookings;
        this.mustCedeParkingSlot = mustCedeParkingSlot;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getMustBlockBookings() {
        return mustBlockBookings;
    }

    public void setMustBlockBookings(Boolean mustBlockBookings) {
        this.mustBlockBookings = mustBlockBookings;
    }

    public Boolean getMustCedeParkingSlot() {
        return mustCedeParkingSlot;
    }

    public void setMustCedeParkingSlot(Boolean mustCedeParkingSlot) {
        this.mustCedeParkingSlot = mustCedeParkingSlot;
    }

}
