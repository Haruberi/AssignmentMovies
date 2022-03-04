package com.movies.assignmentmovies.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CommonResponse<T>{
    public static class ErrorDetails {
        private long status;
        private String message;

        public ErrorDetails(long status, String message) {
            this.status = status;
            this.message = message;
        }

        public long getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
    private Boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorDetails errorDetails;

    public CommonResponse(T payload) {
        this.payload = payload;
        this.success = true;
        this.errorDetails = null;
    }

    public CommonResponse(long status, String message) {
        this.payload = null;
        this.success = false;
        this.errorDetails = new ErrorDetails(status, message);
    }

    public T getPayload() {
        return payload;
    }
    public Boolean getSuccess() {
        return success;
    }
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }
}
