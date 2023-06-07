package kz.iitu.pcsystem.dto;

import kz.iitu.pcsystem.entity.Motherboard;
import kz.iitu.pcsystem.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MotherboardDto {
    private String manufacturer;
    private String model;
    private String socket;
    private String supportedCPUMicroArchitectures;
    private String chipset;
    private String technologies;
    private String formFactor;
    private Integer memorySlotCount;
    private String supportedMemoryTypes;
    private Integer maxMemory;
    private Integer sata3SlotCount;
    private Integer ssdSlotCount;
    private String supportedSsdFormFactor;
    private String m2Slots;
    private String pcieSlots;
    private String pcieVersion;
    private String audioCodec;
    private String internalConnectors;
    private String powerSupplyConnectors;
    private String bios;
    private BigDecimal length;
    private BigDecimal width;
    private String id;
    private BigDecimal price;
    private Boolean isAvailable;
    private String uri;
    private String imageUri;

    public static MotherboardDto fromEntities(Motherboard motherboardEntity, Product productEntity) {
        MotherboardDto motherboardDto = new MotherboardDto();
        motherboardDto.setManufacturer(motherboardEntity.getManufacturer());
        motherboardDto.setModel(motherboardEntity.getModel());
        motherboardDto.setSocket(motherboardEntity.getSocket());
        motherboardDto.setSupportedCPUMicroArchitectures(motherboardEntity.getSupportedCPUMicroArchitectures());
        motherboardDto.setChipset(motherboardEntity.getChipset());
        motherboardDto.setTechnologies(motherboardEntity.getTechnologies());
        motherboardDto.setFormFactor(motherboardEntity.getFormFactor());
        motherboardDto.setMemorySlotCount(motherboardEntity.getMemorySlotCount());
        motherboardDto.setSupportedMemoryTypes(motherboardEntity.getSupportedMemoryTypes());
        motherboardDto.setMaxMemory(motherboardEntity.getMaxMemory());
        motherboardDto.setSata3SlotCount(motherboardEntity.getSata3SlotCount());
        motherboardDto.setSsdSlotCount(motherboardEntity.getSsdSlotCount());
        motherboardDto.setSupportedSsdFormFactor(motherboardEntity.getSupportedSsdFormFactor());
        motherboardDto.setM2Slots(motherboardEntity.getM2Slots());
        motherboardDto.setPcieSlots(motherboardEntity.getPcieSlots());
        motherboardDto.setPcieVersion(motherboardEntity.getPcieVersion());
        motherboardDto.setAudioCodec(motherboardEntity.getAudioCodec());
        motherboardDto.setInternalConnectors(motherboardEntity.getInternalConnectors());
        motherboardDto.setPowerSupplyConnectors(motherboardEntity.getPowerSupplyConnectors());
        motherboardDto.setBios(motherboardEntity.getBios());
        motherboardDto.setLength(motherboardEntity.getLength());
        motherboardDto.setWidth(motherboardEntity.getWidth());
        motherboardDto.setId(productEntity.getComponent().getId());
        motherboardDto.setImageUri(motherboardEntity.getImageUri());
        motherboardDto.setPrice(productEntity.getPrice());
        motherboardDto.setIsAvailable(productEntity.getIsAvailable());
        motherboardDto.setUri(productEntity.getUri());

        return motherboardDto;
    }
}
