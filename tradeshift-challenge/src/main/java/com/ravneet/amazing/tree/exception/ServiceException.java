package com.ravneet.amazing.tree.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public enum ErrorCode {
		SUCCESS(0, "Success"), NODE_NOT_FOUND(1, "Node not found"), PARENT_NOT_FOUND(2, "Parent node not found"),
		PARENT_CHANGE_ILLEGAL(3, "Root node can't be parented");

		private final int code;
		private final String text;

		ErrorCode(int code, String text) {
			this.code = code;
			this.text = text;

		}

		public String getText() {
			return text;
		}

		@Override
		public String toString() {
			return code + ":" + text;
		}
	}

	private ErrorCode errorCode;

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public ServiceException(ErrorCode code) {
		super(code.getText());
		errorCode = code;
	}
}
