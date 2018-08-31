package com.ruiliang.management.exception;

public class SendCodeFailureException extends Exception {

	private static final long serialVersionUID = 986362699980940148L;

	public SendCodeFailureException() {
		super();
	}

	public SendCodeFailureException(String msg) {
		super(msg);
	}
}
