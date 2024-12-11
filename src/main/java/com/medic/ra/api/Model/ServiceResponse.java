package com.medic.ra.api.Model;

import lombok.Data;

@Data
public class ServiceResponse {
    Boolean success;
    String message;

    // Getter y Setter para success
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    // Getter y Setter para message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
