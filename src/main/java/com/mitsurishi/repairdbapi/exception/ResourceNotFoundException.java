/*
 * Class defining ResourceNotFound exception for NOT_FOUND response from HttpStatus. Extends RuntimeException Java class to qualify as throwable.
 */
package com.mitsurishi.repairdbapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // Define class as ResponseStatus bean for component scanning, corresponding to NOT_FOUND HTTP status.
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    // Parameterized constructor yielding formatted message response
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue);
    }
}