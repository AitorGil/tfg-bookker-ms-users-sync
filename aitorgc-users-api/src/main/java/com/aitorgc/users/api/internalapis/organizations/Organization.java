package com.aitorgc.users.api.internalapis.organizations;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@NoArgsConstructor
public class Organization {

    private String id;
    private String name;
    private OrganizationTypes type;
    private OrganizationStatus status;
    private String email;
    private String phone;
    private String image;
    private String cif;
    private String nameContactPerson;
    private String currentLicenceId;
    private AuthTypes authType;
    private String technicalSupportName;
    private String technicalSupportPhone;
    private String technicalSupportEmail;
    private String bookkerContactName;
    private String bookkerContactPhone;
    private String bookkerContactEmail;
    private String videoTeleconferenceAddress;
    private String organizationLanguage;
}
