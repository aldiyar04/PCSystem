package kz.iitu.pcsystem.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class CPUCooler extends BaseEntity {
    private String noiseLevel;
    private String backlight;
    private String mountType;
    private String supportedSockets;
    private String thermalPaste;
    private String fanCount;
    private String height;
    private String width;
    private String length;
    private String weight;
    private String voltage;
    private String connector;
    private String tdp;
    private String coolerHeight;
    private String radiatorMaterial;
    private String baseMaterial;
    private String coolerDesign;
    private String isRotationSpeedAdjustable;
    private String fanBearingType;
    private String cfm;
    private String rotationalSpeed;
    private String fanDimensions;
    private String coolingSystemDesign;

    @Override
    public void setId() {
        setId(UUID.randomUUID().toString());
    }
}
