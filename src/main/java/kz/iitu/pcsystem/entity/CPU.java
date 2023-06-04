package kz.iitu.pcsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class CPU extends BaseEntity {
    private String productUID;
    private String manufacturer;
    private String model;
    private String integratedGraphics;
    private String packaging;
    @Lob
    private String technologies;

    private BigDecimal coreClock;
    private BigDecimal boostCoreClock; // nullable
    private BigDecimal tdp;
    private Integer coreCount;
    private Integer threadCount;
    private String cacheL1;
    private String cacheL2;
    private String cacheL3;
    private Integer lithography;
    private Integer criticalTemperature;
    private String supportedOSes;
    private Integer pciExpressVersion;
    private Integer maxPciExpressChannels;

    private String socket;
    private Integer maxMemory;
    private Integer maxMemoryChannels;
    private String supportedMemoryTypes;

    public String getFullyQualifiedId() {
        return manufacturer + " " + model;
    }

    @Override
    public void setId() {
        setId(manufacturer + " " + model);
    }
}
