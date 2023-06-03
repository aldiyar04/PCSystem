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
public class HDD {
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
}
