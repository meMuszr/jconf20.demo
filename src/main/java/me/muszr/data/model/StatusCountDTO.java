package me.muszr.data.model;

import io.micronaut.core.annotation.Introspected;
import me.muszr.data.model.enums.CaseStatus;

@Introspected
public class StatusCountDTO {
    private CaseStatus status;
    private int count;

    public void setStatus(CaseStatus status) {
        this.status = status;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public CaseStatus getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }

}
