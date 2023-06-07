package kz.iitu.pcsystem.dto;

import kz.iitu.pcsystem.entity.CPUCooler;
import kz.iitu.pcsystem.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CPUCoolerDto {
    private String manufacturer;
    private String model;
    private String type;
    private String socket;
    private String maxTdp;
    private String material;
    private String fanDiameter;
    private String minRotationSpeed;
    private String maxRotationSpeed;
    private String rotationSpeedAdjustment;
    private String maxNoiseLevel;
    private String height;
    private String connector;
    private String airflow;
    private String bearingType;
    private String heatPipesCount;
    private String powerConsumption;
    private String powerVoltage;
    private String lighting;
    private String dimensions;
    private String id;
    private String imageUri;
    private BigDecimal price;
    private Boolean isAvailable;
    private String uri;

    // Constructors, getters, and setters

    public static CPUCoolerDto fromEntities(CPUCooler cpuCoolerEntity, Product productEntity) {
        CPUCoolerDto cpuCoolerDto = new CPUCoolerDto();
        cpuCoolerDto.setManufacturer(cpuCoolerEntity.getManufacturer());
        cpuCoolerDto.setModel(cpuCoolerEntity.getModel());
        cpuCoolerDto.setType(cpuCoolerEntity.getType());
        cpuCoolerDto.setSocket(cpuCoolerEntity.getSocket());
        cpuCoolerDto.setMaxTdp(cpuCoolerEntity.getMaxTdp());
        cpuCoolerDto.setMaterial(cpuCoolerEntity.getMaterial());
        cpuCoolerDto.setFanDiameter(cpuCoolerEntity.getFanDiameter());
        cpuCoolerDto.setMinRotationSpeed(cpuCoolerEntity.getMinRotationSpeed());
        cpuCoolerDto.setMaxRotationSpeed(cpuCoolerEntity.getMaxRotationSpeed());
        cpuCoolerDto.setRotationSpeedAdjustment(cpuCoolerEntity.getRotationSpeedAdjustment());
        cpuCoolerDto.setMaxNoiseLevel(cpuCoolerEntity.getMaxNoiseLevel());
        cpuCoolerDto.setHeight(cpuCoolerEntity.getHeight());
        cpuCoolerDto.setConnector(cpuCoolerEntity.getConnector());
        cpuCoolerDto.setAirflow(cpuCoolerEntity.getAirflow());
        cpuCoolerDto.setBearingType(cpuCoolerEntity.getBearingType());
        cpuCoolerDto.setHeatPipesCount(cpuCoolerEntity.getHeatPipesCount());
        cpuCoolerDto.setPowerConsumption(cpuCoolerEntity.getPowerConsumption());
        cpuCoolerDto.setPowerVoltage(cpuCoolerEntity.getPowerVoltage());
        cpuCoolerDto.setLighting(cpuCoolerEntity.getLighting());
        cpuCoolerDto.setDimensions(cpuCoolerEntity.getDimensions());
        cpuCoolerDto.setPrice(productEntity.getPrice());
        cpuCoolerDto.setIsAvailable(productEntity.getIsAvailable());
        cpuCoolerDto.setUri(productEntity.getUri());
        cpuCoolerDto.setImageUri(cpuCoolerEntity.getImageUri());
        cpuCoolerDto.setId(cpuCoolerEntity.getId());

        return cpuCoolerDto;
    }
}
