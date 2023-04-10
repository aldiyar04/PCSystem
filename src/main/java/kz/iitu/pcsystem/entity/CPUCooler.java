package kz.iitu.pcsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CPUCooler {
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
}
