package com.aitorgc.ms.subscriptions.api.internalapis.users;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Aitor Gil Callejo
 */
@Data
@NoArgsConstructor
public class FindUserResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;
}
