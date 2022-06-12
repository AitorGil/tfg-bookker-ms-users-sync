package com.aitorgc.users.api.model;

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
public interface VehicleBuildingRepository extends JpaRepository<VehicleBuildingEntity, VehicleBuildingPK> {

	VehicleBuildingEntity findByVehicleBuildingPKAndStatus(VehicleBuildingPK vehicleBuildingPK,
			VehicleStatus vehicleStatus);

	List<VehicleBuildingEntity> findAllByVehicleBuildingPKLicensePlate(String licensePlate);

	@Query(nativeQuery = true, value = "SELECT VB.* FROM p_vehicle_building VB INNER JOIN c_building B ON VB.building_id = B.id WHERE B.organization_id = :organization_id AND VB.license_plate = :license_plate")
	List<VehicleBuildingEntity> getOrganizationVehicleBuildings(@Param("license_plate") String licensePlate,
			@Param("organization_id") String organizationId);

	List<VehicleBuildingEntity> findAllByVehicleBuildingPKLicensePlateIn(List<String> licensePlates);

	void deleteAllByVehicleBuildingPKBuildingId(String buildingId);

	void deleteAllByVehicleBuildingPKLicensePlate(String licensePlate);

	void deleteAllByVehicleBuildingPKLicensePlateIn(List<String> licensePlates);

	@Modifying
	@Query(nativeQuery = true, value = "update p_vehicle_building set status='LOCKED' where license_plate=(select license_plate from p_vehicle where user_id = :userId) and status='PENDING_VALIDATION'")
	void declineAllPendingUserVehicleBuildingValidationRequests(@Param("userId") String userId);

	@Modifying
	@Query(nativeQuery = true, value = "update p_vehicle_building set status='VALIDATED' where license_plate=(select license_plate from p_vehicle where user_id = :userId) and status='PENDING_VALIDATION'")
	void acceptAllPendingUserVehicleBuildingValidationRequests(@Param("userId") String userId);
}
