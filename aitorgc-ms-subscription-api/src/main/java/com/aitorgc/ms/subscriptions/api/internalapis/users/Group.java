package com.aitorgc.ms.subscriptions.api.internalapis.users;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String description;
    private GroupTypes type;
    private String buildingId;
    private String microsoftId;

    private String createdBy;
    private Date createdDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
}
