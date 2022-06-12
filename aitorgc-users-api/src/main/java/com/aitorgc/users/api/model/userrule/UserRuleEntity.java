package com.aitorgc.users.api.model.userrule;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.aitorgc.users.api.model.BaseEntity;

/**
 *
 * @author Aitor Gil Callejo
 * 
 */
@Entity
@Table(name = "c_user_rule")
public class UserRuleEntity extends BaseEntity {

    private static final long serialVersionUID = 3513413898706318275L;

    @Column(nullable = false, length = 255)
    @NotEmpty(message = "* Name can't be empty")
    private String name;

    @Embedded
    private WorkingDay workingDay;

    private Boolean requiresApproval = false;

    @Column(name = "organization_id", nullable = false, length = 36)
    private String organizationId;

    @Column(name = "allow_automatic_workstation_booking")
    private Boolean allowAutomaticWorkstationBooking;

    @Column(name = "allow_manual_workstation_booking")
    private Boolean allowManualWorkstationBooking;

    @Column(name = "days_advance_workstation_booking")
    private Integer daysAdvanceWorkstationBooking;

    @Column(name = "days_advance_space_booking")
    private Integer daysAdvanceSpaceBooking;

    @Column(name = "days_advance_dinningArea_booking")
    private Integer daysAdvanceDinningAreaBooking;

    @Column(name = "advance_parking_booking")
    private Long advanceParkingBooking;

    protected UserRuleEntity() {
	// do nothing
    }

    public UserRuleEntity(String name, WorkingDay workingDay, Boolean requiresApproval, String organizationId,
	    Integer daysAdvanceWorkstationBooking, Integer daysAdvanceSpaceBooking,
	    Integer daysAdvanceDinningAreaBooking, Long advanceParkingBooking) {
	this.name = name;
	this.workingDay = workingDay;
	this.organizationId = organizationId;
	this.requiresApproval = requiresApproval;
	this.daysAdvanceWorkstationBooking = daysAdvanceWorkstationBooking;
	this.daysAdvanceSpaceBooking = daysAdvanceSpaceBooking;
	this.daysAdvanceDinningAreaBooking = daysAdvanceDinningAreaBooking;
	this.advanceParkingBooking = advanceParkingBooking;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public WorkingDay getWorkingDay() {
	return workingDay;
    }

    public void setWorkingDay(WorkingDay workingDay) {
	this.workingDay = workingDay;
    }

    public Boolean getRequiresApproval() {
	return requiresApproval;
    }

    public void setRequiresApproval(Boolean requiresApproval) {
	this.requiresApproval = requiresApproval;
    }

    public String getOrganizationId() {
	return organizationId;
    }

    public void setOrganizationId(String organizationId) {
	this.organizationId = organizationId;
    }

    public Boolean getAllowAutomaticWorkstationBooking() {
	return allowAutomaticWorkstationBooking;
    }

    public void setAllowAutomaticWorkstationBooking(Boolean allowAutomaticWorkstationBooking) {
	this.allowAutomaticWorkstationBooking = allowAutomaticWorkstationBooking;
    }

    public Integer getDaysAdvanceWorkstationBooking() {
	return daysAdvanceWorkstationBooking;
    }

    public void setDaysAdvanceWorkstationBooking(Integer daysAdvanceWorkstationBooking) {
	this.daysAdvanceWorkstationBooking = daysAdvanceWorkstationBooking;
    }

    public Integer getDaysAdvanceSpaceBooking() {
	return daysAdvanceSpaceBooking;
    }

    public void setDaysAdvanceSpaceBooking(Integer daysAdvanceSpaceBooking) {
	this.daysAdvanceSpaceBooking = daysAdvanceSpaceBooking;
    }

    public Integer getDaysAdvanceDinningAreaBooking() {
	return daysAdvanceDinningAreaBooking;
    }

    public void setDaysAdvanceDinningAreaBooking(Integer daysAdvanceDinningAreaBooking) {
	this.daysAdvanceDinningAreaBooking = daysAdvanceDinningAreaBooking;
    }

    public Long getAdvanceParkingBooking() {
	return advanceParkingBooking;
    }

    public void setAdvanceParkingBooking(Long advanceParkingBooking) {
	this.advanceParkingBooking = advanceParkingBooking;
    }

    public Boolean getAllowManualWorkstationBooking() {
	return allowManualWorkstationBooking;
    }

    public void setAllowManualWorkstationBooking(Boolean allowManualWorkstationBooking) {
	this.allowManualWorkstationBooking = allowManualWorkstationBooking;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(advanceParkingBooking, allowAutomaticWorkstationBooking,
		allowManualWorkstationBooking, daysAdvanceDinningAreaBooking, daysAdvanceSpaceBooking,
		daysAdvanceWorkstationBooking, name, organizationId, requiresApproval, workingDay);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	UserRuleEntity other = (UserRuleEntity) obj;
	return Objects.equals(advanceParkingBooking, other.advanceParkingBooking)
		&& Objects.equals(allowAutomaticWorkstationBooking, other.allowAutomaticWorkstationBooking)
		&& Objects.equals(allowManualWorkstationBooking, other.allowManualWorkstationBooking)
		&& Objects.equals(daysAdvanceDinningAreaBooking, other.daysAdvanceDinningAreaBooking)
		&& Objects.equals(daysAdvanceSpaceBooking, other.daysAdvanceSpaceBooking)
		&& Objects.equals(daysAdvanceWorkstationBooking, other.daysAdvanceWorkstationBooking)
		&& Objects.equals(name, other.name) && Objects.equals(organizationId, other.organizationId)
		&& Objects.equals(requiresApproval, other.requiresApproval)
		&& Objects.equals(workingDay, other.workingDay);
    }

}
