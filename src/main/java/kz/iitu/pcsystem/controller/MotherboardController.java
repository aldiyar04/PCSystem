package kz.iitu.pcsystem.controller;

import kz.iitu.pcsystem.dto.MotherboardDto;
import kz.iitu.pcsystem.dto.ProductDto;
import kz.iitu.pcsystem.entity.Motherboard;
import kz.iitu.pcsystem.filtering.CustomSpecificationsBuilder;
import kz.iitu.pcsystem.repository.MotherboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/motherboards")
@AllArgsConstructor
public class MotherboardController {
    private final MotherboardRepository motherboardRepository;

    @GetMapping
    public Page<MotherboardDto> getMotherboards(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(value = "search", required = false) String search) {
        Page<Motherboard> motherboardPage = motherboardRepository.findAll(CustomSpecificationsBuilder.getSpecification(search), PageRequest.of(page, size));

        List<Motherboard> motherboards = new ArrayList<>(motherboardPage.getContent());

        // Sort motherboards by products list size in descending order
        motherboards.sort(Comparator.comparingInt(motherboard -> motherboard.getProducts().size()));
        Collections.reverse(motherboards);

        // Convert to DTOs
        List<MotherboardDto> motherboardDtos = motherboards.stream()
                .map(motherboard -> MotherboardDto.fromEntities(motherboard, motherboard.getProducts().get(0)))
                .collect(Collectors.toList());

        return new PageImpl<>(motherboardDtos, PageRequest.of(page, size), motherboardPage.getTotalElements());
    }

    @GetMapping("/{id}/products")
    public List<ProductDto> getMotherboards(@PathVariable("id") UUID id) {
        return motherboardRepository.findById(id).get().getProducts().stream()
                .map(ProductDto::fromEntity)
                .toList();
    }
}
