package com.aitorgc.users.api.model.userrule;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Aitor Gil Callejo
 * 
 */
@Embeddable
public class WorkingDay implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long time;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private WorkingDayTypes workingDayType;

    protected WorkingDay() {
	// do nothing
    }

    public WorkingDay(Long time, WorkingDayTypes workingDayType) {
	this.time = time;
	this.workingDayType = workingDayType;
    }

    public Long getTime() {
	return time;
    }

    public void setTime(Long time) {
	this.time = time;
    }

    public WorkingDayTypes getWorkingDayType() {
	return workingDayType;
    }

    public void setWorkingDayType(WorkingDayTypes workingDayType) {
	this.workingDayType = workingDayType;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public int hashCode() {
	return Objects.hash(time, workingDayType);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	WorkingDay other = (WorkingDay) obj;
	return Objects.equals(time, other.time) && workingDayType == other.workingDayType;
    }

}
