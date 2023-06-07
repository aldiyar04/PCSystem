package kz.iitu.pcsystem.dto;

import kz.iitu.pcsystem.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {
    private String componentId;
    private BigDecimal price;
    private Boolean isAvailable;
    private String uri;

    public static ProductDto fromEntity(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setComponentId(product.getComponent().getId());
        productDto.setPrice(product.getPrice());
        productDto.setIsAvailable(product.getIsAvailable());
        productDto.setUri(product.getUri());
        return productDto;
    }
}
