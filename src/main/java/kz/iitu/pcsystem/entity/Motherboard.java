package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
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
    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column
    private String socket;

    @Column
    private String supportedCPUMicroArchitectures;

    @Column
    private String chipset;

    @Column
    private String technologies;

    @Column
    private String formFactor;

    @Column
    private Integer memorySlotCount;

    @Column(columnDefinition = "text")
    @Lob
    private String supportedMemoryTypes; // map from website in same format as CPU

    @Column
    private Integer maxMemory;

    @Column
    private Integer sata3SlotCount; // map

    @Column
    private Integer ssdSlotCount; // map

    @Column
    private String supportedSsdFormFactor; // map

    @Column
    private String m2Slots;

    @Column
    private String pcieSlots;

    @Column
    private String pcieVersion;

    @Column
    private String audioCodec;

    @Column
    @Lob
    private String internalConnectors;

    @Column
    private String powerSupplyConnectors;

    @Column
    private String bios;

    @Column
    private BigDecimal length;

    @Column
    private BigDecimal width;

    @Override
    public void setId() {
        setId(manufacturer + " " + model);
    }
}
