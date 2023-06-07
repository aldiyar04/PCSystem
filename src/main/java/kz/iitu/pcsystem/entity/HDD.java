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
public class HDD extends ComponentEntity {
    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(name = "device_type")
    private String deviceType;

    @Column
    private String line;

    @Column(name = "form_factor")
    private String formFactor;

    @Column(name = "interface_type")
    private String interfaceType;

    @Column(name = "interface_speed_gbps")
    private String interfaceSpeedGbps;

    @Column(name = "capacity_gb")
    private String capacityGB;

    @Column(name = "buffer_mb")
    private String bufferMB;

    @Column(name = "disk_count")
    private String diskCount;

    @Column(name = "head_count")
    private String headCount;

    @Column(name = "active_power_consumption_watt")
    private String activePowerConsumptionWatt;

    @Column(name = "idle_power_consumption_watt")
    private String idlePowerConsumptionWatt;

    @Column
    private String mtbf;

    @Column
    private String dimensions;

    @Override
    public void setId() {
        String id = manufacturer + "_" + model;
        setId(id.replaceAll("\\s", "_").replaceAll("/", "--"));
    }
}
