package me.muszr.data.repository;

import java.sql.ResultSet;
import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.async.AsyncCrudRepository;
import me.muszr.data.model.CovidCase;
import me.muszr.data.model.StatusCountDTO;
import me.muszr.data.model.enums.CaseStatus;

@JdbcRepository(dialect = Dialect.POSTGRES)
public abstract class CovidCaseRepository implements AsyncCrudRepository<CovidCase, Integer> {
    private final JdbcOperations jdbcOperations;

    public CovidCaseRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public abstract CompletableFuture<CovidCase> find(int sourceId);
    public abstract CompletableFuture<List<CovidCase>> listTop10OrderByLastUpdatedDesc();

    public abstract CompletableFuture<Void> update(@Id Integer id, CaseStatus status);

    public abstract CompletableFuture<Boolean> existsBySourceId(int sourceId);

    @Transactional
    public EnumMap<CaseStatus, Integer> getStats() {
        String sql = "SELECT status AS status, COUNT(id) AS count FROM covid_case GROUP BY status";
        return jdbcOperations.<EnumMap<CaseStatus, Integer>>prepareStatement(sql, statement -> {
            ResultSet resultSet = statement.executeQuery();
            return jdbcOperations.entityStream(resultSet, StatusCountDTO.class)
                    .collect(Collectors.toMap(StatusCountDTO::getStatus, StatusCountDTO::getCount,
                            (l, r) -> {
                                throw new IllegalArgumentException("Duplicate keys");
                            },
                            () -> new EnumMap<CaseStatus, Integer>(CaseStatus.class)));
        });
    }
}
