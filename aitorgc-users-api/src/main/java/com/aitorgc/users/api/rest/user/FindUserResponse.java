package com.aitorgc.users.api.rest.user;

import java.io.Serializable;

/**
 *
 * @author Aitor Gil Callejo
 */
public class FindUserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private final UserView user;

    public FindUserResponse(UserView user) {
        this.user = user;
    }

    public UserView getUser() {
        return user;
    }
}
