package com.aitorgc.ms.subscriptions.api.internalapis.users;

import java.io.Serializable;

/**
 *
 * @author Aitor Gil Callejo
 */
public class UpdateUserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;

    protected UpdateUserResponse() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
