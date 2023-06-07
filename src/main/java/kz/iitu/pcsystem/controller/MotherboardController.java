package kz.iitu.pcsystem.controller;

import kz.iitu.pcsystem.dto.MotherboardDto;
import kz.iitu.pcsystem.filtering.CustomSpecificationsBuilder;
import kz.iitu.pcsystem.repository.MotherboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/motherboards")
@AllArgsConstructor
public class MotherboardController {
    private final MotherboardRepository motherboardRepository;

    @GetMapping
    public Page<MotherboardDto> getMotherboards(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(value = "search") String search) {
        return motherboardRepository.findAll(CustomSpecificationsBuilder.getSpecification(search), PageRequest.of(page, size))
                .map(motherboard -> MotherboardDto.fromEntities(motherboard, motherboard.getProducts().get(0)));
//        return motherboardRepository.findAllSortedByProductCountDesc(CustomSpecificationsBuilder.getSpecification(search),
//                        PageRequest.of(page, size))
//                .map(motherboard -> MotherboardDto.fromEntities(motherboard, motherboard.getProducts().get(0)));
    }
}
