package me.muszr.service;

import static me.muszr.data.model.enums.CaseStatus.convert;

import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.annotation.Prototype;
import me.muszr.data.model.CovidCase;
import me.muszr.data.model.enums.CaseStatus;
import me.muszr.data.repository.CovidCaseRepository;
import me.muszr.model.CaseEvent;
import me.muszr.websocket.CaseWebSocket;

@Prototype
public class CaseEventService {

    private final CaseWebSocket caseWebSocket;
    private final CovidCaseRepository covidCaseRepository;
    private final Logger logger = LoggerFactory.getLogger(CaseEventService.class);

    @Inject
    public CaseEventService(CaseWebSocket caseWebSocket, CovidCaseRepository covidCaseRepository) {
        this.caseWebSocket = caseWebSocket;
        this.covidCaseRepository = covidCaseRepository;
    }

    public EnumMap<CaseStatus, Integer> getStats() {
        return covidCaseRepository.getStats();
    }

    public CompletableFuture<List<CovidCase>> getRecentCases() {
        return this.covidCaseRepository.listTop10OrderByLastUpdatedDesc();
    } 

    public CompletableFuture<Void> handleEvent(CaseEvent caseEvent) {
        var covidCaseExistsFuture = covidCaseRepository.existsBySourceId(caseEvent.getId());

        var covidCaseFuture = covidCaseExistsFuture.thenComposeAsync(exists -> {
            var status = convert(caseEvent.getStatus());
            if (exists) {
                return covidCaseRepository.find(caseEvent.getId()).thenApplyAsync((covidCase) -> {
                    covidCase.setStatus(status);
                    return covidCase;
                });
            } else {
                var newCovidCase = new CovidCase(caseEvent.getId(), caseEvent.getTestDate(), caseEvent.getDateOfBirth(),
                        caseEvent.getName(), caseEvent.getLocation(), status);
                return CompletableFuture.completedFuture(newCovidCase);
            }
        });

        var dbInsertOrUpdateFuture = covidCaseFuture.thenComposeAsync((covidCase) -> covidCase.getId() == null
                ? covidCaseRepository.save(covidCase)
                : covidCaseRepository.update(covidCase.getId(), covidCase.getStatus()).thenApply((Void) -> covidCase));

        var msgFuture = this.caseWebSocket.sendMessage(caseEvent);

        return CompletableFuture.allOf(dbInsertOrUpdateFuture, msgFuture);
    }

}
