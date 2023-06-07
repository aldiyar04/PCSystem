package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
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
public class Motherboard extends ComponentEntity {
    private String manufacturer;
    private String model;
    private String socket;
    private String supportedCPUMicroArchitectures;
    private String chipset;
    private String technologies;
    private String formFactor;
    private Integer memorySlotCount;
    private String supportedMemoryTypes; // map from website in same format as CPU
    private Integer maxMemory;
    private Integer sata3SlotCount; // map
    private Integer ssdSlotCount; // map
    private String supportedSsdFormFactor; // map
    private String m2Slots;
    private String pcieSlots;
    private String pcieVersion;
    private String audioCodec;
    private String internalConnectors;
    private String powerSupplyConnectors;
    private String bios;
    private BigDecimal length;
    private BigDecimal width;

    @Override
    public void setId() {
        setId(manufacturer + " " + model);
    }
}
