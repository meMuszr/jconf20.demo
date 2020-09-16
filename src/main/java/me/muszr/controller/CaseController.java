package me.muszr.controller;

import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import me.muszr.data.model.CovidCase;
import me.muszr.data.model.enums.CaseStatus;
import me.muszr.service.CaseEventService;

@Controller("/api/case")
public class CaseController {

    private final CaseEventService caseEventService;

    @Inject
    public CaseController(CaseEventService caseEventService) {
        this.caseEventService = caseEventService;

    }

    @Get("/stats")
    public EnumMap<CaseStatus, Integer> getStats() {
        return caseEventService.getStats();
    }

    @Get("/recents")
    public CompletableFuture<List<CovidCase>> getRecentCases() {
        return caseEventService.getRecentCases();
    }

}
