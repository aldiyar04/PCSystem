package kz.iitu.pcsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Case {
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
}
