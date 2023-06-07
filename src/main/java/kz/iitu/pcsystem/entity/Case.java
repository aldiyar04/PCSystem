package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "casee")

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)

@JsonIgnoreProperties(ignoreUnknown = true)
public class Case extends ComponentEntity {
    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(name = "case_form_factor")
    private String caseFormFactor;

    @Column(name = "compatible_form_factors")
    private String compatibleFormFactors;

    @Column
    private String material;

    @Column(name = "front_panel_construction")
    private String frontPanelConstruction;

    @Column(name = "built_in_power_supply")
    private String builtInPowerSupply;

    @Column(name = "power_supply_position")
    private String powerSupplyPosition;

    @Column(name = "hdd_mounting")
    private String hddMounting;

    @Column(name = "internal_2_5_inch_drive_bays")
    private String internal2_5InchDriveBays;

    @Column(name = "internal_3_5_inch_drive_bays")
    private String internal3_5InchDriveBays;

    @Column
    private String buttons;

    @Column
    private String indicators;

    @Column(name = "expansion_slots_count")
    private String expansionSlotsCount;

    @Column(name = "graphics_card_installation_type")
    private String graphicsCardInstallationType;

    @Column(name = "max_graphics_card_length")
    private String maxGraphicsCardLength;

    @Column(name = "max_cpu_cooler_height")
    private String maxCpuCoolerHeight;

    @Column(name = "included_cooling")
    private String includedCooling;

    @Column(name = "supported_fan_sizes")
    private String supportedFanSizes;

    @Column(name = "max_liquid_cooler_length")
    private String maxLiquidCoolerLength;

    @Column(name = "supported_liquid_cooler_locations")
    private String supportedLiquidCoolerLocations;

    @Column(name = "dust_filters")
    private String dustFilters;

    @Column(name = "additional_connectors")
    private String additionalConnectors;

    @Column
    private String color;

    @Column
    private String dimensions;

    @Override
    public void setId() {
        setId(manufacturer + " " + model);
    }
}
