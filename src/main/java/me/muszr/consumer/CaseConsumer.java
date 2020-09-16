package me.muszr.consumer;

import java.util.concurrent.CompletableFuture;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import me.muszr.model.CaseEvent;

public interface CaseConsumer {
    CompletableFuture<Void> recieveCaseEvent(@KafkaKey String key, CaseEvent caseEvent);

}
