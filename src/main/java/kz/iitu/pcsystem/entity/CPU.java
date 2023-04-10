package kz.iitu.pcsystem.entity;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CPU {
    private String manufacturer;
    private String model;
    private String integratedGraphics;
    private String packaging;

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
}
