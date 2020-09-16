package me.muszr.data.model.enums;

import static java.util.Map.entry;

import java.util.Map;

import me.muszr.model.enums.CaseEventStatus;

public enum CaseStatus {
    POTENTIAL, CONFIRMED, NEGATIVE, RECOVERED, DEAD;

    private static Map<CaseEventStatus, CaseStatus> mapping;
    static {
        mapping = Map.ofEntries(
            entry(CaseEventStatus.POTENTIAL, POTENTIAL),
            entry(CaseEventStatus.CONFIRMED, CONFIRMED),
            entry(CaseEventStatus.NEGATIVE, NEGATIVE),
            entry(CaseEventStatus.RECOVERED, RECOVERED),
            entry(CaseEventStatus.DEAD, DEAD)
        );
    }
    public static CaseStatus convert(CaseEventStatus status) {
        return mapping.get(status);
    }


}
