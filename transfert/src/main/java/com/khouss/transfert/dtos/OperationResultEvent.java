package com.khouss.transfert.dtos;

public class OperationResultEvent {
    private String transferId;
    private String operationType;
    private String status;
    private String errorMessage;

    public OperationResultEvent() {}

    public OperationResultEvent(String transferId, String operationType, String status, String errorMessage) {
        this.transferId = transferId;
        this.operationType = operationType;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getTransferId() { return transferId; }
    public void setTransferId(String transferId) { this.transferId = transferId; }

    public String getOperationType() { return operationType; }
    public void setOperationType(String operationType) { this.operationType = operationType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}