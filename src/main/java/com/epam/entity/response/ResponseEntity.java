package com.epam.entity.response;

import com.epam.dao.DaoResult;

public class ResponseEntity {
    private final String status;
    private final String message;
    private final Object[] responseObjects;

    public ResponseEntity(String status, String message, Object... responseObjects) {
        this.status = status;
        this.message = message;
        this.responseObjects = responseObjects;
    }

    public ResponseEntity(DaoResult result, Object... responseObjects) {
        this.status = result.getStatus();
        this.message = result.getMessage();
        this.responseObjects = responseObjects;
    }

    public String getStatus() {
        return status;
    }

    public Object[] getResponseObjects() {
        return responseObjects;
    }
}
