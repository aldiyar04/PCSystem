package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Entity

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoCard extends BaseEntity {
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

    @Override
    public void setId() {
        setId(UUID.randomUUID().toString());
    }
}
