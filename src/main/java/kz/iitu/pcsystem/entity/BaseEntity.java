package kz.iitu.pcsystem.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass

@SuperBuilder
@NoArgsConstructor
@Data
public abstract class BaseEntity {
    @Id
    @Setter(AccessLevel.PACKAGE)
    private String id;

    public abstract void setId();
}
