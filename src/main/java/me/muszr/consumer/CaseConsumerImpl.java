package me.muszr.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;
import me.muszr.models.CaseEvent;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class CaseConsumerImpl  {
    public CaseConsumerImpl() {
        logger.info("instantiated");
    }

    private final Logger logger = LoggerFactory.getLogger(CaseConsumerImpl.class);

    @Topic("cases-events")
    public void recieveCaseEvent(@KafkaKey final String key, @Body final CaseEvent caseEvent) {
        logger.debug(caseEvent.toString());
    }

}
