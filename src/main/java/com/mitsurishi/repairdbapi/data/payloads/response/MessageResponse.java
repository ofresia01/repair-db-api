/*
 * Class defining a potential response payload, MessageResponse. Will display message to requesting client.
 */
package com.mitsurishi.repairdbapi.data.payloads.response;

public class MessageResponse {
    // Private member variables
    private String message;

    // Parameterized and default constructors
    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse() {};

    // Accessors and mutators
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}