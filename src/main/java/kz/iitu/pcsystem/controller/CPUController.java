package kz.iitu.pcsystem.controller;

import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.repository.CPURepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cpus")
@AllArgsConstructor
public class CPUController {
    private final CPURepository cpuRepository;

    @GetMapping
    public Page<CPU> getImageById(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return cpuRepository.findAllSortedByProductCountDesc(PageRequest.of(page, size));
    }
}
