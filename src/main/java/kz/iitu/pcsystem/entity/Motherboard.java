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
public class Motherboard extends BaseEntity {
    private String productUID;
    private String manufacturer;
    private String model;
    private String supportedOSes;
    private String formFactor;
    private String supportedCPUs;
    private String chipset;
    private String features;
    private String socket;
    private String supportedCPUGenerations;
    private String bios;
    private String internalUsbPorts;
    private String maxMemory;
    private String isECCSupported;
    private String supportedMemoryTypes;
    private String memoryConnectorCount;
    private String raid;
    private String isNVMeBootSupported;
    private Integer m2ConnectorCount;
    private String sataConnectorCount;
    private String isIntelOptaneMemory;
    private String bluetooth;
    private String network;
    private String supportedWiFiFeatures;
    private String networkController;
    private String multiGpuSupport;
    private String sound;
    private String soundChannelCount;
    private String soundAdapterChipset;
    private String keyboard;
    private String connectors;
    private String mouse;
    private String pciExpressSlots;
    private String pciExpressVersions;
    private Integer usbPortCount;
    private String backPanelAudioConnectors;
    private String backPanelVideoConnectors;
    private String powerSupplyConnectorCounts;

    @Override
    public void setId() {
        setId(UUID.randomUUID().toString());
    }
}
