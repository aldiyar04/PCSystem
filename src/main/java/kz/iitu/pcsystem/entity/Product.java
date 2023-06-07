package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

import static kz.iitu.pcsystem.entity.ComponentEntity.*;

@Entity

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "component_id", nullable = false)
    private ComponentEntity component;

    @Column(nullable = false)
    private String componentType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false)
    private String uri;

    @Transient
    private String componentId; // helper field, not for DB

    public void setComponent(ComponentEntity component) {
        this.component = component;

        if (component instanceof CPU) setComponentType(COMPONENT_TYPE_CPU);
        if (component instanceof Motherboard) setComponentType(COMPONENT_TYPE_MOTHERBOARD);
        if (component instanceof CPUCooler) setComponentType(COMPONENT_TYPE_CPU_COOLER);
        if (component instanceof VideoCard) setComponentType(COMPONENT_TYPE_VIDEO_CARD);
        if (component instanceof Memory) setComponentType(COMPONENT_TYPE_MEMORY);
        if (component instanceof SSD) setComponentType(COMPONENT_TYPE_SSD);
        if (component instanceof HDD) setComponentType(COMPONENT_TYPE_HDD);
        if (component instanceof PowerSupply) setComponentType(COMPONENT_TYPE_POWER_SUPPLY);
        if (component instanceof Case) setComponentType(COMPONENT_TYPE_CASE);
    }
}
