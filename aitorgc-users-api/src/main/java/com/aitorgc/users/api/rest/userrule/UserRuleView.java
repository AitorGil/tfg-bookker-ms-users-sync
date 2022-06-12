package com.aitorgc.users.api.rest.userrule;

import java.io.Serializable;
import java.util.Objects;

import com.aitorgc.users.api.model.userrule.UserRuleEntity;
import com.aitorgc.users.api.model.userrule.WorkingDay;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRuleView implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;

    private final String organizationId;

    private final String name;

    private final WorkingDay workingDay;

    private final boolean requiresApproval;

    private final boolean allowAutomaticWorkstationBooking;

    private final boolean allowManualWorkstationBooking;

    private final Integer daysAdvanceWorkstationBooking;

    private final Integer daysAdvanceSpaceBooking;

    private final Integer daysAdvanceDinningAreaBooking;

    private final Long advanceParkingBooking;

    public UserRuleView(UserRuleEntity dao) {
	this.id = dao.getId();
	this.organizationId = dao.getOrganizationId();
	this.name = dao.getName();
	this.workingDay = dao.getWorkingDay();
	this.requiresApproval = Boolean.TRUE.equals(dao.getRequiresApproval());
	this.allowAutomaticWorkstationBooking = Boolean.TRUE.equals(dao.getAllowAutomaticWorkstationBooking());
	this.allowManualWorkstationBooking = Boolean.TRUE.equals(dao.getAllowManualWorkstationBooking());
	this.daysAdvanceWorkstationBooking = dao.getDaysAdvanceWorkstationBooking();
	this.daysAdvanceSpaceBooking = dao.getDaysAdvanceSpaceBooking();
	this.daysAdvanceDinningAreaBooking = dao.getDaysAdvanceDinningAreaBooking();
	this.advanceParkingBooking = dao.getAdvanceParkingBooking();
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public String getId() {
	return id;
    }

    public String getOrganizationId() {
	return organizationId;
    }

    public String getName() {
	return name;
    }

    public WorkingDay getWorkingDay() {
	return workingDay;
    }

    public boolean isRequiresApproval() {
	return requiresApproval;
    }

    public boolean isAllowAutomaticWorkstationBooking() {
	return allowAutomaticWorkstationBooking;
    }

    public boolean isAllowManualWorkstationBooking() {
	return allowManualWorkstationBooking;
    }

    public Integer getDaysAdvanceWorkstationBooking() {
	return daysAdvanceWorkstationBooking;
    }

    public Integer getDaysAdvanceSpaceBooking() {
	return daysAdvanceSpaceBooking;
    }

    public Integer getDaysAdvanceDinningAreaBooking() {
	return daysAdvanceDinningAreaBooking;
    }

    public Long getAdvanceParkingBooking() {
	return advanceParkingBooking;
    }

    @Override
    public int hashCode() {
	return Objects.hash(advanceParkingBooking, allowAutomaticWorkstationBooking, allowManualWorkstationBooking,
		daysAdvanceDinningAreaBooking, daysAdvanceSpaceBooking, daysAdvanceWorkstationBooking, id, name,
		organizationId, requiresApproval, workingDay);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	UserRuleView other = (UserRuleView) obj;
	return Objects.equals(advanceParkingBooking, other.advanceParkingBooking)
		&& allowAutomaticWorkstationBooking == other.allowAutomaticWorkstationBooking
		&& allowManualWorkstationBooking == other.allowManualWorkstationBooking
		&& Objects.equals(daysAdvanceDinningAreaBooking, other.daysAdvanceDinningAreaBooking)
		&& Objects.equals(daysAdvanceSpaceBooking, other.daysAdvanceSpaceBooking)
		&& Objects.equals(daysAdvanceWorkstationBooking, other.daysAdvanceWorkstationBooking)
		&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
		&& Objects.equals(organizationId, other.organizationId) && requiresApproval == other.requiresApproval
		&& Objects.equals(workingDay, other.workingDay);
    }

    @Override
    public String toString() {
	return "UserRuleView [id=" + id + ", organizationId=" + organizationId + ", name=" + name + ", workingDay="
		+ workingDay + ", requiresApproval=" + requiresApproval + ", allowAutomaticWorkstationBooking="
		+ allowAutomaticWorkstationBooking + ", allowManualWorkstationBooking=" + allowManualWorkstationBooking
		+ ", daysAdvanceWorkstationBooking=" + daysAdvanceWorkstationBooking + ", daysAdvanceSpaceBooking="
		+ daysAdvanceSpaceBooking + ", daysAdvanceDinningAreaBooking=" + daysAdvanceDinningAreaBooking
		+ ", advanceParkingBooking=" + advanceParkingBooking + "]";
    }

}
