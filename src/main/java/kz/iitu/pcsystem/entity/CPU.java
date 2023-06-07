package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)

@JsonIgnoreProperties(ignoreUnknown = true)
public class CPU extends ComponentEntity {
    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String socket; // comp

    @Column(nullable = false)
    private Integer coreCount;

    @Column(nullable = false)
    private Integer threadCount;

    @Column(nullable = false)
    private BigDecimal coreClock;

    @Column
    private BigDecimal boostCoreClock; // nullable

    @Column
    private String microArchitecture;

    @Column
    private String cacheL1;

    @Column
    private String cacheL2;

    @Column
    private String cacheL3;

    @Column
    private String supportedMemoryTypes; // comp

    @Column
    private Integer maxMemory; // comp

    @Column
    private Boolean isEccMemorySupported; // comp probably

    @Column
    private String integratedGraphics;

    @Column
    private Integer lithography;

    @Column
    private BigDecimal tdp;

    @Column
    private BigDecimal maxTdp;

    @Column
    @Lob
    private String technologies;

    @Column
    @Lob
    private String instructions;

    @Column
    private Integer criticalTemperature;

    @Override
    public void setId() {
        setId(manufacturer + " " + model);
    }
}
