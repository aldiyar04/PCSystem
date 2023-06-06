package kz.iitu.pcsystem.image;

import kz.iitu.pcsystem.repository.CPURepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
@AllArgsConstructor
public class ImageController {
    private final CPURepository cpuRepository;

    @GetMapping("/cpus/{id}")
    public byte[] getImageById(@PathVariable("id") String id) {
        return cpuRepository.findImageById(id).getImage();
    }
}
