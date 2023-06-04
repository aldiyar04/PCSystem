package kz.iitu.pcsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Memory {
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
}
