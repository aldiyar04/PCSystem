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
public class PowerSupply {
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal length;
    private String formFactor;
    private BigDecimal power;
    private String frequency; // changed from BigDecimal to String because of this: "Cannot deserialize value of type `java.math.BigDecimal` from String "50 - 60": not a valid representation"
    private String inputVoltage; // changed from BigDecimal to String because of this: "Cannot deserialize value of type `java.math.BigDecimal` from String "100-240": not a valid representation"
    private String securityFeatures;
    private String certificate;
    private BigDecimal fanDiameter;
    private String pfcPresence;
    private String isRgbBacklight;
    private BigDecimal efficiencyPercentage;
    private String coolingSystem;
    private String powerSupplyManagement;
    private String canDisconnectCables;
    private BigDecimal cableLength;
    private String cpuPowerConnector;
    private Integer sataConnectorCount;
    private Integer molexConnectorCount;
    private String videoCardPowerConnector;
    private Integer floppyConnectorCount;
    private String motherboardPowerConnector;
}
