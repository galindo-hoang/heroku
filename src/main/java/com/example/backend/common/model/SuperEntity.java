package com.example.backend.common.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@Data
@MappedSuperclass
public class SuperEntity {
    @Version
    @Column(name = "version")
    private Long version;
}
