package com.aitorgc.users.api.model;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public interface UserWorkingStatusExceptionRepository extends JpaRepository<UserWorkingStatusExceptionEntity, String> {

	@Query("FROM UserWorkingStatusExceptionDAO wse WHERE wse.userId = :userId AND (wse.startDate BETWEEN :rangeStartDate AND :rangeEndDate OR wse.endDate between :rangeStartDate AND :rangeEndDate OR (wse.startDate <= :rangeStartDate AND wse.endDate >= :rangeEndDate))")
	List<UserWorkingStatusExceptionEntity> getUserWorkingStatusExceptions(@Param("userId") String userId,
			@Param("rangeStartDate") OffsetDateTime rangeStartDate, @Param("rangeEndDate") OffsetDateTime rangeEndDate);

	List<UserWorkingStatusExceptionEntity> findBySeriesMasterId(String seriesMasterId);

	List<UserWorkingStatusExceptionEntity> findByUserIdAndStartDateGreaterThanEqual(String id, OffsetDateTime now);

	UserWorkingStatusExceptionEntity findByUserIdAndStartDateAndEndDate(String id, OffsetDateTime nowStart,
			OffsetDateTime nowEnd);

	@Query("FROM UserWorkingStatusExceptionDAO wse WHERE wse.userId = :userId AND (:now BETWEEN wse.startDate AND wse.endDate OR wse.startDate = :now OR wse.endDate = :now)")
	List<UserWorkingStatusExceptionEntity> getTodayUserWorkingStatus(@Param("userId") String userId,
			@Param("now") OffsetDateTime now);

	List<UserWorkingStatusExceptionEntity> findAllByWorkingStatusId(String workingStatusId);

	List<UserWorkingStatusExceptionEntity> findAllBySeriesMasterIdInAndType(List<String> seriesMasterIds,
			WorkingStatusEventTypes type);

	List<UserWorkingStatusExceptionEntity> findByUserIdAndEndDateGreaterThanEqual(String userId, OffsetDateTime now);

	List<UserWorkingStatusExceptionEntity> findAllByUserId(String userId);

	@Query("FROM UserWorkingStatusExceptionDAO uwse WHERE uwse.userId IN (SELECT u.id FROM UserDAO u WHERE u.organizationId = :organizationId)")
	List<UserWorkingStatusExceptionEntity> findAllByUserIdIn(@Param("organizationId") String organizationId);

	@Modifying
	@Query(nativeQuery = true, value = "delete from c_user_working_status_exceptions where user_id = :userId")
	void deleteAllByUserId(@Param("userId") String userId);
}
