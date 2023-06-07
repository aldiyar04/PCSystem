package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
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
public class PowerSupply extends ComponentEntity {
    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(name = "form_factor")
    private String formFactor;

    @Column
    private String power;

    @Column(name = "pfc")
    private String pfc;

    @Column(name = "standard_compliance")
    private String standardCompliance;

    @Column
    private String efficiency;

    @Column(name = "motherboard_connectors")
    private String motherboardConnectors;

    @Column(name = "graphics_card_connectors")
    private String graphicsCardConnectors;

    @Column(name = "pci_e_6_pin_count")
    private String pciE6PinCount;

    @Column(name = "pci_e_2_pin_count")
    private String pciE2PinCount;

    @Column(name = "molex_4_pin_count")
    private String molex4PinCount;

    @Column(name = "sata_count")
    private String sataCount;

    @Column(name = "input_voltage")
    private String inputVoltage;

    @Column(name = "input_frequency")
    private String inputFrequency;

    @Column(name = "output_current_3v3")
    private String outputCurrent3V3;

    @Column(name = "output_current_5v")
    private String outputCurrent5V;

    @Column(name = "output_current_12v")
    private String outputCurrent12V;

    @Column(name = "fan_size")
    private String fanSize;

    @Column(name = "included_items")
    private String includedItems;

    @Column(name = "protection_systems")
    private String protectionSystems;

    @Column
    private String length;

    @Column
    private String dimensions;

    @Override
    public void setId() {
        String id = manufacturer + "_" + model;
        setId(id.replaceAll("\\s", "_").replaceAll("/", "--"));
    }
}
