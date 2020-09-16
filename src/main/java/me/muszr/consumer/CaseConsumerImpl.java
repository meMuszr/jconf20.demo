package me.muszr.consumer;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;
import me.muszr.model.CaseEvent;
import me.muszr.service.CaseEventService;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class CaseConsumerImpl implements CaseConsumer {

    private final Logger logger = LoggerFactory.getLogger(CaseConsumerImpl.class);
    private final CaseEventService caseEventService;

    @Inject
    public CaseConsumerImpl(CaseEventService caseEventService) {
        this.caseEventService = caseEventService;
    }

    @Topic("cases-events")
    public CompletableFuture<Void> recieveCaseEvent(@KafkaKey final String key, @Body final CaseEvent caseEvent) {
        logger.trace("Recieved a new event");
        logger.debug("Event recieved: " + caseEvent.toString());
        return caseEventService.handleEvent(caseEvent);
    }

}