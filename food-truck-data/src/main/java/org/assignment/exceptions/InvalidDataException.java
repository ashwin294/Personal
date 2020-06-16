package org.assignment.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception to identify invalid data passed to builders/constructors/utils etc.
 */
public class InvalidDataException extends Exception {
	private static final Logger log = LoggerFactory.getLogger(InvalidDataException.class);

	public InvalidDataException(String errorMessage) {
		super(errorMessage);
	}

	public InvalidDataException(String errorMessage, Exception exception) {
		super(errorMessage, exception);
	}

}
