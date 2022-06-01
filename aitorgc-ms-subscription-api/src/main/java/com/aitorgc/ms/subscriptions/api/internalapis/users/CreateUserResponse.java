package com.aitorgc.ms.subscriptions.api.internalapis.users;

import java.io.Serializable;

/**
 *
 * @author Aitor Gil Callejo
 */
public class CreateUserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;

    protected CreateUserResponse() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
