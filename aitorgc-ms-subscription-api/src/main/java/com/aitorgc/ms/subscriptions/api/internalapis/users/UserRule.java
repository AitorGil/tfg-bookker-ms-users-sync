package com.aitorgc.ms.subscriptions.api.internalapis.users;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRule implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String organizationId;
    private String name;
    private WorkingDay workingDay;
    private boolean requiresApproval;
    private boolean allowAutomaticWorkstationBooking;
    private boolean allowManualWorkstationBooking;
    private Integer daysAdvanceWorkstationBooking;
    private Integer daysAdvanceSpaceBooking;
    private Integer daysAdvanceDinningAreaBooking;
    private Long advanceParkingBooking;

}
