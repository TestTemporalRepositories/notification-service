package org.payment.notification.workflows;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface NotifyWorkflow {

    @WorkflowMethod
    String notify(String message);
}
