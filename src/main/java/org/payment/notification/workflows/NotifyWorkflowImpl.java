package org.payment.notification.workflows;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;
import org.payment.notification.activities.PaymentActivity;

import java.time.Duration;

@Slf4j
public class NotifyWorkflowImpl implements NotifyWorkflow {

    private static final String NOTIFY_ACTIVITY_QUEUE = "NOTIFY-TASK-QUEUE";

    @Override
    public String notify(String message) {
        log.info("child workflow processing...");
        return getNotifyActivity().notify(message);
    }

    private PaymentActivity getNotifyActivity() {
        ActivityOptions activityOptions = ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(300L))
                .setTaskQueue(NOTIFY_ACTIVITY_QUEUE)
                .build();
        return Workflow.newActivityStub(PaymentActivity.class, activityOptions);
    }
}
