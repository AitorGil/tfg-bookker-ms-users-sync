package com.aitorgc.ms.subscriptions.api.internalapis.users;

import java.io.Serializable;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Aitor Gil Callejo
 */
@Data
@RequiredArgsConstructor
public class CreateUserResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;

}
