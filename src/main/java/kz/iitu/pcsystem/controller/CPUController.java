package kz.iitu.pcsystem.controller;

import kz.iitu.pcsystem.dto.CPUDto;
import kz.iitu.pcsystem.dto.ProductDto;
import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.filtering.CustomSpecificationsBuilder;
import kz.iitu.pcsystem.repository.CPURepository;
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
@RequestMapping("/cpus")
@AllArgsConstructor
public class CPUController {
    private final CPURepository cpuRepository;

    @GetMapping
    public Page<CPUDto> getCPUs(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(value = "search", required = false) String search) {
        Page<CPU> cpuPage = cpuRepository.findAll(CustomSpecificationsBuilder.getSpecification(search), PageRequest.of(page, size));

        List<CPU> cpus = new ArrayList<>(cpuPage.getContent());

        // Sort cpus by products list size in descending order
        cpus.sort(Comparator.comparingInt(cpu -> cpu.getProducts().size()));
        Collections.reverse(cpus);

        // Convert to DTOs
        List<CPUDto> cpuDtos = cpus.stream()
                .map(cpu -> CPUDto.fromEntities(cpu, cpu.getProducts().get(0)))
                .collect(Collectors.toList());

        return new PageImpl<>(cpuDtos, PageRequest.of(page, size), cpuPage.getTotalElements());
    }

    @GetMapping("/{id}/products")
    public List<ProductDto> getCPUs(@PathVariable("id") String id) {
        return cpuRepository.findById(id).get().getProducts().stream()
                .map(ProductDto::fromEntity)
                .toList();
    }
}
