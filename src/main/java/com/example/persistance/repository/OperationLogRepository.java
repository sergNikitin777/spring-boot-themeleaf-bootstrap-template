package com.example.persistance.repository;

import com.example.persistance.entity.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "events", path = "events")
public interface OperationLogRepository extends JpaRepository<OperationLog, Long> {
}
