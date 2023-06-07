package kz.iitu.pcsystem.dto;

import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CPUDto {
    private String manufacturer;
    private String model;
    private String socket;
    private Integer coreCount;
    private Integer threadCount;
    private BigDecimal coreClock;
    private BigDecimal boostCoreClock;
    private String microArchitecture;
    private String cacheL1;
    private String cacheL2;
    private String cacheL3;
    private String supportedMemoryTypes;
    private Integer maxMemory;
    private Boolean isEccMemorySupported;
    private String integratedGraphics;
    private Integer lithography;
    private BigDecimal tdp;
    private BigDecimal maxTdp;
    private String technologies;
    private String instructions;
    private Integer criticalTemperature;
    private String id;
    private String imageUri;
    private BigDecimal price;
    private Boolean isAvailable;
    private String uri;

    public static CPUDto fromEntities(CPU cpuEntity, Product productEntity) {
        CPUDto dto = new CPUDto();

        // Mapping CPU entity fields to DTO
        dto.setManufacturer(cpuEntity.getManufacturer());
        dto.setModel(cpuEntity.getModel());
        dto.setSocket(cpuEntity.getSocket());
        dto.setCoreCount(cpuEntity.getCoreCount());
        dto.setThreadCount(cpuEntity.getThreadCount());
        dto.setCoreClock(cpuEntity.getCoreClock());
        dto.setBoostCoreClock(cpuEntity.getBoostCoreClock());
        dto.setMicroArchitecture(cpuEntity.getMicroArchitecture());
        dto.setCacheL1(cpuEntity.getCacheL1());
        dto.setCacheL2(cpuEntity.getCacheL2());
        dto.setCacheL3(cpuEntity.getCacheL3());
        dto.setSupportedMemoryTypes(cpuEntity.getSupportedMemoryTypes());
        dto.setMaxMemory(cpuEntity.getMaxMemory());
        dto.setIsEccMemorySupported(cpuEntity.getIsEccMemorySupported());
        dto.setIntegratedGraphics(cpuEntity.getIntegratedGraphics());
        dto.setLithography(cpuEntity.getLithography());
        dto.setTdp(cpuEntity.getTdp());
        dto.setMaxTdp(cpuEntity.getMaxTdp());
        dto.setTechnologies(cpuEntity.getTechnologies());
        dto.setInstructions(cpuEntity.getInstructions());
        dto.setCriticalTemperature(cpuEntity.getCriticalTemperature());
        dto.setId(cpuEntity.getId());
        dto.setImageUri(cpuEntity.getImageUri());

        // Mapping Product entity fields to DTO
        dto.setPrice(productEntity.getPrice());
        dto.setIsAvailable(productEntity.getIsAvailable());
        dto.setUri(productEntity.getUri());

        return dto;
    }
}
