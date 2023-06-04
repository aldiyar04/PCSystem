package kz.iitu.pcsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoCard {
    private String productUID;
    private String manufacturer;
    private String gpuManufacturer;
    private String technologies;
    private String gpuSeries;
    private BigDecimal throughput;
    private String ports;
    private Integer maxMonitorCount;
    private String rgb;
    private String videoMemory;
    private String videoMemoryType;
    private String gpuModel;
    private Integer lithography;
    private Integer coreClock;
    private Integer boostCoreClock;
    private Integer shadingUnitCount;
    private BigDecimal occupiedSlotCount;
    private BigDecimal length;
    private Integer recommendedWattage;
    private String powerConnector;
    private String wattageConsumption;
    private String nvidia3VisionSupport;
    private Boolean isPhysXSupported;
    private String maxDisplayResolution;
    private String generalPurposeGPUComputingSupport;
}
