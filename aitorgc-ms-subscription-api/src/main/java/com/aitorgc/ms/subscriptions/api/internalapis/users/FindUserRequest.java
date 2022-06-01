package com.aitorgc.ms.subscriptions.api.internalapis.users;

import java.io.Serializable;

/**
 *
 * @author Aitor Gil Callejo
 */
public class FindUserRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private String upn;

    private String microsoftId;

    public FindUserRequest(String email, String upn, String microsoftId) {
        this.email = email;
        this.upn = upn;
        this.microsoftId = microsoftId;
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
