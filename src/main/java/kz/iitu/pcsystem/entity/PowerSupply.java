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
public class PowerSupply extends ComponentEntity {
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

    @Override
    public void setId() {
        setId(UUID.randomUUID().toString());
    }
}
