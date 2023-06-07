package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
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
public class VideoCard extends ComponentEntity {
    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(name = "gpu_manufacturer")
    private String gpuManufacturer;

    @Column(name = "gpu_series")
    private String gpuSeries;

    @Column(name = "chipset_model")
    private String chipsetModel;

    @Column(columnDefinition = "text")
    @Lob
    private String technologies;

    @Column(name = "video_cpu_frequency")
    private String videoCPUFrequency;

    @Column(name = "video_memory_frequency")
    private String videoMemoryFrequency;

    @Column(name = "video_memory_type")
    private String videoMemoryType;

    @Column(name = "video_memory_capacity")
    private String videoMemoryCapacity;

    @Column(name = "video_memory_bus_width")
    private String videoMemoryBusWidth;

    @Column(name = "memory_throughput")
    private String memoryThroughput;

    @Column(name = "connector_interface")
    private String connectorInterface;

    @Column(name = "power_supply_slots")
    private String powerSupplySlots;

    @Column(name = "min_power_supply_wattage")
    private String minPowerSupplyWattage;

    @Column
    private String length;

    @Override
    public void setId() {
        setId(manufacturer + " " + model);
    }
}
