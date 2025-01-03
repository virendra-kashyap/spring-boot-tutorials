package com.virendra.kafka.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "RECORDS")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "VALUE", nullable = false)
    private String value;

    public Record(String value) {
        this.value = value;
    }

}
