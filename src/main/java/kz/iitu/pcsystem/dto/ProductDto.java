package kz.iitu.pcsystem.dto;

import kz.iitu.pcsystem.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductDto {
    private UUID componentId;
    private BigDecimal price;
    private Boolean isAvailable;
    private String uri;

    public static ProductDto fromEntity(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setComponentId(product.getComponent().getIid());
        productDto.setPrice(product.getPrice());
        productDto.setIsAvailable(product.getIsAvailable());
        productDto.setUri(productDto.getUri());
        return productDto;
    }
}
