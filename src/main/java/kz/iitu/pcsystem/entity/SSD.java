package kz.iitu.pcsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SSD {
    private String manufacturer;
    private String model;
    private String formFactor;
    private String series;
    private String chipType;
    private Integer capacity;
    private String writeSpeed;
    private String readSpeed;
    private String interfaceThroughput;
    private String pcieVersion;
    private String height;
    private String width;
    private String length;
    private String noiseLevel;
    private String workingTemperature;
    private String meanTimeBetweenFailures;
    private String idleEnergyConsumption;
    private String activeEnergyConsumption;
}
