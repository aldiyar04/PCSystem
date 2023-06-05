package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.io.BigDecimalParser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

@Entity

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)

@JsonIgnoreProperties(ignoreUnknown = true)
public class CPU extends BaseEntity {
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

    @Column(nullable = false)
    private BigDecimal boostCoreClock; // nullable

    @Column(nullable = false)
    private String microArchitecture;

    @Column(nullable = false)
    private String cacheL1;

    @Column(nullable = false)
    private String cacheL2;

    @Column(nullable = false)
    private String cacheL3;

    @Column(nullable = false)
    private String supportedMemoryTypes; // comp

    @Column(nullable = false)
    private Integer maxMemory; // comp

    @Column(nullable = false)
    private Boolean isEccMemorySupported; // comp probably

    @Column(nullable = false)
    private String integratedGraphics;

    @Column(nullable = false)
    private Integer lithography;

    @Column(nullable = false)
    private BigDecimal tdp;

    @Column
    private BigDecimal maxTdp;

    @Column(nullable = false)
    @Lob
    private String technologies;

    @Column(nullable = false)
    @Lob
    private String instructions;

    @Column(nullable = false)
    private Integer criticalTemperature;

    @Override
    public void setId() {
        setId(manufacturer + " " + model);
    }
}
