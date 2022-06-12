package com.aitorgc.users.api.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Aitor Gil Callejo
 */
public interface VehicleRepository extends JpaRepository<VehicleEntity, VehiclePK> {

    List<VehicleEntity> findAllByVehiclePKUserId(String userId);

    List<VehicleEntity> findAllByVehiclePKLicensePlate(String licensePlate);

    @Query("FROM VehicleDAO v WHERE v.vehiclePK.userId IN (SELECT u.id FROM UserDAO u WHERE u.organizationId = :organizationId)")
    List<VehicleEntity> findAllWhereVehiclePKUserIdIn(@Param("organizationId") String organizationId);

    @Query("FROM VehicleDAO v WHERE v.vehiclePK.userId = :userId")
    List<VehicleEntity> findAllUserVehiclesByUserId(@Param("userId") String userId);

    @Query("FROM VehicleDAO v WHERE v.vehiclePK.userId = :userId")
    Page<VehicleEntity> findAllUserVehiclesByUserId(@Param("userId") String userId, Pageable pageable);

    long countByVehiclePKUserId(String userId);

}
