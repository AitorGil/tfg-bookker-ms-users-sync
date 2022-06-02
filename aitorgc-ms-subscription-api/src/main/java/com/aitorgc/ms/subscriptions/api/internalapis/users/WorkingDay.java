package com.aitorgc.ms.subscriptions.api.internalapis.users;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * @author Aitor Gil Callejo
 * 
 */
@Data
public class WorkingDay implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long time;

    private WorkingDayTypes workingDayType;

}
