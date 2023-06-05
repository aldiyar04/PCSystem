package kz.iitu.pcsystem.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Entity

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseEntity {
    private String componentId;
    private String componentType;
    private String packaging;
    private BigDecimal price;
    private String uri;

    @Override
    public void setId() {
        setId(UUID.randomUUID().toString());
    }
}
