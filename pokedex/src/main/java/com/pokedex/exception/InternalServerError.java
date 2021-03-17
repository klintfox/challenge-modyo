package com.pokedex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends Exception {

	private static final long serialVersionUID = 2114955052452482150L;

	private String message;
	private String path;

	public InternalServerError(String message) {
		this.message = message;
	}

	public InternalServerError(String message, String path) {
		super();
		this.message = message;
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
