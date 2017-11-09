package com.sdjt.rzgatewaygov.gateway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties
public class GatewayResponse {

    String BUSID;
    String Message;
    boolean IsSuccess;


    public GatewayResponse() {
    }

    public String getBUSID() {
        return BUSID;
    }

    public void setBUSID(String BUSID) {
        this.BUSID = BUSID;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

    @Override
    public String toString() {
        return "GatewayResponse{" +
                "Message='" + Message + '\'' +
                ", IsSuccess=" + IsSuccess +
                '}';
    }
}
