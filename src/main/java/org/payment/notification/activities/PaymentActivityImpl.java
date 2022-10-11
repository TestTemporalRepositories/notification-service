package org.payment.notification.activities;

import io.temporal.activity.Activity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentActivityImpl implements PaymentActivity {

    @Override
    public String notify(String message) {
        log.info("Отправка..");
        return "Сообщение отправлено!";
    }
}
