package com.khouss.compte.dtos;

public class DebitResultEvent {
    private String transferId;
    private boolean success;
    private String message;

    public DebitResultEvent() {}

    public DebitResultEvent(String transferId, boolean success, String message) {
        this.transferId = transferId;
        this.success = success;
        this.message = message;
    }

    public String getTransferId() { return transferId; }
    public void setTransferId(String transferId) { this.transferId = transferId; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}