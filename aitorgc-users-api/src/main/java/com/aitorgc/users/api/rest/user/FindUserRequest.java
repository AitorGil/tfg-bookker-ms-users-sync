package com.aitorgc.users.api.rest.user;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.ToString;

/**
 *
 * @author Aitor Gil Callejo
 */
@ToString
public class FindUserRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 255)
    @Email(message = "email is not valid")
    private String email;

    @Size(max = 255)
    @Email(message = "upn is not valid")
    private String upn;

    private String microsoftId;

    protected FindUserRequest() {
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getUpn() {
	return upn;
    }

    public void setUpn(String upn) {
	this.upn = upn;
    }

    public String getMicrosoftId() {
	return microsoftId;
    }

    public void setMicrosoftId(String microsoftId) {
	this.microsoftId = microsoftId;
    }

}
