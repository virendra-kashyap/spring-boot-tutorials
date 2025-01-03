package com.virendra.kafka.repository;

import com.virendra.kafka.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
