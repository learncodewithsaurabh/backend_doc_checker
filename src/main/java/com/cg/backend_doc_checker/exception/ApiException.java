package com.cg.backend_doc_checker.exception;

public class ApiException extends RuntimeException {
    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }
}
