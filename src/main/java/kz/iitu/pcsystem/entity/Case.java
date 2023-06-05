package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Case extends BaseEntity {
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal length;
    private BigDecimal weight;
    private String formFactor;
    private String backlight;
    private String type;
    private String material;
    private String usbPorts;
    private Integer expansionSlotCount;
    private String audioJacks;
    private String portsLocation;
    private String mounting;
    private String indicators;
    private BigDecimal maxVideoCardLength;
    private String dustFilter;
    private String isWindowOnSide;
    private Integer compartment25Count;
    private Integer compartment35Count;
    private String supportedFanTypes;
    private String isLiquidCoolingPlace;
    private BigDecimal maxCpuCoolerHeight;
    private String cpuCoolingIncluded;
    private String powerSupplyLocation;

    @Override
    public void setId() {
        setId(UUID.randomUUID().toString());
    }
}
