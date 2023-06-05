package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)

@JsonIgnoreProperties(ignoreUnknown = true)
public class Memory extends BaseEntity {
    private String productUID;
    private String manufacturer;
    private String model;
    private String packaging;
    private String series;
    private String formFactor;
    private String amount;
    private String configuration;
    private String clockFrequency;
    private String throughput;
    private String ports;
    private String moduleCount;
    private String memoryType;
    private String casLatency;
    private String memoryTimingScheme;
    private String rasToCasDelay;
    private String rowPrechargeDelay;
    private String isXmpSupported;
    private String voltage;
    private String isRgbBacklight;
    private String isCoolingRadiator;
    private String backlightSync;

    @Override
    public void setId() {
        setId(UUID.randomUUID().toString());
    }
}
