package com.zequiz.shared.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class NamedDateAudit extends DateAudit {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    protected UUID id;

    @NotBlank
    private String name;
}
