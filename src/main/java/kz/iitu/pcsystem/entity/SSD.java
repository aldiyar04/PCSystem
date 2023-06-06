package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@JsonIgnoreProperties(ignoreUnknown = true)
public class SSD extends Component {
    private String manufacturer;
    private String model;
    private String formFactor;
    private String series;
    private String chipType;
    private String capacity;
    private String writeSpeed;
    private String readSpeed;
    private String interfaceThroughput;
    private String pcieVersion;
    private String height;
    private String width;
    private String length;
    private String noiseLevel;
    private String workingTemperature;
    private String meanTimeBetweenFailures;
    private String idleEnergyConsumption;
    private String activeEnergyConsumption;

    @Override
    public void setId() {
        setId(UUID.randomUUID().toString());
    }
}
