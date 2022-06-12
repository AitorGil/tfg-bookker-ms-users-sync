package com.aitorgc.users.api.model;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aitor Gil Callejo
 */
@Entity
@Table(name = "c_user_working_status_exceptions")
public class UserWorkingStatusExceptionEntity extends BaseEntity {

	private static final long serialVersionUID = 3513413898706318275L;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private WorkingStatusEventTypes type;

	@NotNull
	@Column(nullable = false)
	private OffsetDateTime startDate;

	@NotNull
	@Column(nullable = false)
	private OffsetDateTime endDate;

	@Column
	private String seriesMasterId;

	@Column(nullable = false, length = 36)
	@NotEmpty(message = "* User ID can't be empty")
	@NotNull
	private String userId;

	@Column(nullable = false, length = 36)
	@NotEmpty(message = "* User ID can't be empty")
	@NotNull
	private String workingStatusId;

	protected UserWorkingStatusExceptionEntity() {
	}

	public UserWorkingStatusExceptionEntity(@NotNull WorkingStatusEventTypes type, @NotNull OffsetDateTime startDate,
			@NotNull OffsetDateTime endDate, @NotEmpty(message = "* User ID can't be empty") @NotNull String userId,
			@NotEmpty(message = "* User ID can't be empty") @NotNull String workingStatusId) {
		super();
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.workingStatusId = workingStatusId;
	}

	public WorkingStatusEventTypes getType() {
		return type;
	}

	public void setType(WorkingStatusEventTypes type) {
		this.type = type;
	}

	public OffsetDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(OffsetDateTime startDate) {
		this.startDate = startDate;
	}

	public OffsetDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}

	public String getSeriesMasterId() {
		return seriesMasterId;
	}

	public void setSeriesMasterId(String seriesMasterId) {
		this.seriesMasterId = seriesMasterId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWorkingStatusId() {
		return workingStatusId;
	}

	public void setWorkingStatusId(String workingStatusId) {
		this.workingStatusId = workingStatusId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
