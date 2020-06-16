package org.assignment.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception to identify problems when making a call to the remote service.
 */
public class APIRequestException extends Exception {
	private static final Logger log = LoggerFactory.getLogger(APIRequestException.class);

	public APIRequestException(String errorMessage) {
		super(errorMessage);
	}

	public APIRequestException(String errorMessage, Exception exception) {
		super(errorMessage, exception);
	}

}
