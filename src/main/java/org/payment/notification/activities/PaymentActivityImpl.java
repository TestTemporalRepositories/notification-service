package org.payment.notification.activities;

import io.temporal.activity.Activity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentActivityImpl implements PaymentActivity {

    @Override
    public String notify(String message) {
        try {
            log.info("Отправка..");
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw Activity.wrap(new Exception("error..."));
        }
        return "Сообщение отправлено!";
    }
}
