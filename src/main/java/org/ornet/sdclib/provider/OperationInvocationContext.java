/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Pulic License version 3.0.
 * http://www.gnu.org/licenses/gpl-3.0.de.html
 *
 */
/**
 * @author besting
 * @Copyright (C) SurgiTAIX AG
 */
package org.ornet.sdclib.provider;

/**
 *
 * @author besting
 */
public class OperationInvocationContext {

    private String operationHandle;
    private String operationTarget;
    private long transactionId;

    public OperationInvocationContext(String operationHandle, String operationTarget, long transactionId) {
        this.operationHandle = operationHandle;
        this.operationTarget = operationTarget;
        this.transactionId = transactionId;
    }

    public void setOperationHandle(String operationHandle) {
        this.operationHandle = operationHandle;
    }

    public String getOperationHandle() {
        return operationHandle;
    }

    public void setOperationTarget(String operationTarget) {
        this.operationTarget = operationTarget;
    }

    public String getOperationTarget() {
        return operationTarget;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getTransactionId() {
        return transactionId;
    }

}
