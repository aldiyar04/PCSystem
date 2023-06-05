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
public class HDD extends BaseEntity {
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal length;
    private BigDecimal weight;
    private String capacity;
    private String formFactor;
    private String spindleSpeed;
    private String series;
    private String type;
    private String model;
    private String interfacee;
    private String purpose;
    private String mtbf;
    private Integer bufferMemory;
    private String maxOverloadOn;
    private BigDecimal averageWaitingTime;
    private BigDecimal noiseLevel;
    private String maxOverloadOff;

    @Override
    public void setId() {
        setId(UUID.randomUUID().toString());
    }
}
