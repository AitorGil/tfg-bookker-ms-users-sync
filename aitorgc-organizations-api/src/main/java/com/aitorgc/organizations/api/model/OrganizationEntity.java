package com.aitorgc.organizations.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.aitorgc.organizations.api.model.util.BaseEntity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 *
 * @author Aitor Gil Callejo
 */
@Getter
@Setter
@Entity
@Table(name = "c_organization")
public class OrganizationEntity extends BaseEntity {

	private static final long serialVersionUID = 3513413898706318275L;

	@Column(nullable = false, length = 255)
	@NotEmpty(message = "* Name can't be empty")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull
	private OrganizationTypes type;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull
	private OrganizationStatus status;

	@Column(nullable = false, length = 255)
	@Email(message = "Email should be valid")
	private String email;

	@Column(nullable = false, length = 255)
	@NotEmpty(message = "* Phone can't be empty")
	private String phone;

	@Column(length = 255)
	private String image;

	@Column(length = 50)
	@Size(max = 50)
	private String cif;

	@Column(length = 150)
	@Size(max = 150)
	private String nameContactPerson;

	@Column(name = "current_licence_id", nullable = true, length = 36)
	private String currentLicenceId;

	@Enumerated(EnumType.STRING)
	@Column(name = "auth_type", nullable = false, length = 255)
	@NotNull
	private AuthTypes authType;

	@Column(name = "technical_support_name", length = 255)
	private String technicalSupportName;

	@Column(name = "technical_support_phone", length = 255)
	private String technicalSupportPhone;

	@Column(name = "technical_support_email", length = 255)
	private String technicalSupportEmail;

	@Column(name = "bookker_contact_name", length = 255)
	private String bookkerContactName;

	@Column(name = "bookker_contact_phone", length = 255)
	private String bookkerContactPhone;

	@Column(name = "bookker_contact_email", length = 255)
	private String bookkerContactEmail;

	@Column(name = "video_teleconference_address", length = 100)
	private String videoTeleconferenceAddress;

	@Column(nullable = false, length = 45)
	@NonNull
	@NotNull
	private String organizationLanguage;

	protected OrganizationEntity() {
	}

	public OrganizationEntity(String name, OrganizationTypes type, String email, String phone, OrganizationStatus status) {
		this.name = name;
		this.type = type;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.authType = AuthTypes.NATIVE;
	}

	public OrganizationEntity(String name, OrganizationTypes type, String email, String phone, OrganizationStatus status,
			AuthTypes authType, String organizationLanguage) {
		this.name = name;
		this.type = type;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.authType = authType;
		this.organizationLanguage = organizationLanguage;
	}

}
