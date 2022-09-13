package com.ppg.digidoc.eh.exception;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 2555321976707340233L;

	public ValidationException() {
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Exception exception) {
		super(exception);
	}

	@SuppressWarnings("rawtypes")
	public ValidationException(Class clazz, String key, String value) {
		super(String.format("Status of the %s having [%s=%s] is not active", org.springframework.util.StringUtils.capitalize(clazz.getSimpleName()),key,value));
	}


}


