package com.aitorgc.ms.subscriptions.internalapis.organizations;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
public class Organization implements Serializable {

	private static final long serialVersionUID = 6785717567397060568L;

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
