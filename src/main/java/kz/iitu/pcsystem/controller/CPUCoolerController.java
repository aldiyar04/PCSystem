package kz.iitu.pcsystem.controller;

import kz.iitu.pcsystem.dto.CPUCoolerDto;
import kz.iitu.pcsystem.dto.ProductDto;
import kz.iitu.pcsystem.entity.CPUCooler;
import kz.iitu.pcsystem.filtering.CustomSpecificationsBuilder;
import kz.iitu.pcsystem.repository.CPUCoolerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cpucoolers")
@AllArgsConstructor
public class CPUCoolerController {
    private final CPUCoolerRepository cpuCoolerRepository;

    @GetMapping
    public Page<CPUCoolerDto> getCPUCoolers(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(value = "search", required = false) String search) {
        Page<CPUCooler> cpuCoolerPage = cpuCoolerRepository.findAll(CustomSpecificationsBuilder.getSpecification(search), PageRequest.of(page, size));

        List<CPUCooler> cpuCoolers = new ArrayList<>(cpuCoolerPage.getContent());

        // Sort cpuCoolers by products list size in descending order
        cpuCoolers.sort(Comparator.comparingInt(cpuCooler -> cpuCooler.getProducts().size()));
        Collections.reverse(cpuCoolers);

        // Convert to DTOs
        List<CPUCoolerDto> cpuCoolerDtos = cpuCoolers.stream()
                .map(cpuCooler -> CPUCoolerDto.fromEntities(cpuCooler, cpuCooler.getProducts().get(0)))
                .collect(Collectors.toList());

        return new PageImpl<>(cpuCoolerDtos, PageRequest.of(page, size), cpuCoolerPage.getTotalElements());
    }

    @GetMapping("/{id}/products")
    public List<ProductDto> getCPUCoolers(@PathVariable("id") String id) {
        return cpuCoolerRepository.findById(id).get().getProducts().stream()
                .map(ProductDto::fromEntity)
                .toList();
    }
}
