package com.khouss.compte.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProcessedOperation {

    @Id
    private String operationKey;

    public ProcessedOperation() {}

    public ProcessedOperation(String operationKey) {
        this.operationKey = operationKey;
    }

    public String getOperationKey() { return operationKey; }
    public void setOperationKey(String operationKey) { this.operationKey = operationKey; }

}
