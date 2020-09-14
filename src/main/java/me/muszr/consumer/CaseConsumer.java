package me.muszr.consumer;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import me.muszr.models.CaseEvent;

public interface CaseConsumer {

    void recieveCaseEvent(@KafkaKey String key, CaseEvent caseEvent);

}
