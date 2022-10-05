package org.payment.notification.config;

import org.payment.notification.activities.PaymentActivity;
import org.payment.notification.config.properties.WorkerProperties;
import org.payment.notification.config.properties.Workers;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import org.payment.notification.workflows.NotifyWorkflowImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Map;

@RequiredArgsConstructor
public class WorkerFactoryStarter implements ApplicationListener<ContextRefreshedEvent> {

    private final Map<String, WorkerProperties> workerPropertiesMap;
    private final WorkerFactory workerFactory;
    private final PaymentActivity paymentActivity;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        setPaymentServiceWorker(workerPropertiesMap.get(Workers.NOTIFICATION.name()));
        workerFactory.start();
    }

    private void setPaymentServiceWorker(WorkerProperties workerProperties) {
        Worker worker = workerFactory.newWorker(workerProperties.getQueueName());
        worker.registerActivitiesImplementations(paymentActivity);
        worker.registerWorkflowImplementationTypes(NotifyWorkflowImpl.class);
    }
}
