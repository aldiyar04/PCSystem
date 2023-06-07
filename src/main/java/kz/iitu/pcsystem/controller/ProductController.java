package kz.iitu.pcsystem.controller;

import kz.iitu.pcsystem.dto.ProductDto;
import kz.iitu.pcsystem.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/{componentType}/{componentId}")
    public List<ProductDto> getMotherboards(@PathVariable("componentId") String componentId,
                                            @PathVariable("componentType") String componentType) {
        return productRepository.findAllByComponentId(componentId, componentType).stream()
                .map(ProductDto::fromEntity)
                .toList();
    }
}
