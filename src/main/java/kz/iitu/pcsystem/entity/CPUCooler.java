package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)

@JsonIgnoreProperties(ignoreUnknown = true)
public class CPUCooler extends ComponentEntity {
    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column
    private String type;

    @Column
    private String socket;

    @Column(name = "max_tdp")
    private String maxTdp;

    @Column
    private String material;

    @Column(name = "fan_diameter")
    private String fanDiameter;

    @Column(name = "min_rotation_speed")
    private String minRotationSpeed;

    @Column(name = "max_rotation_speed")
    private String maxRotationSpeed;

    @Column(name = "rotation_speed_adjustment")
    private String rotationSpeedAdjustment;

    @Column(name = "max_noise_level")
    private String maxNoiseLevel;

    @Column
    private String height;

    @Column
    private String connector;

    @Column
    private String airflow;

    @Column(name = "bearing_type")
    private String bearingType;

    @Column(name = "heat_pipes_count")
    private String heatPipesCount;

    @Column(name = "power_consumption")
    private String powerConsumption;

    @Column(name = "power_voltage")
    private String powerVoltage;

    @Column
    private String lighting;

    @Column
    private String dimensions;

    @Override
    public void setId() {
        String id = manufacturer + "_" + model;
        setId(id.replaceAll("\\s", "_").replaceAll("/", "--"));
    }
}
