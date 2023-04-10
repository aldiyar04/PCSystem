package kz.iitu.pcsystem.scraper.technodom;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.pcsystem.entity.CPUCooler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class CPUCoolerScraper {
    private final TechnoDomScraper technoDomScraper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Map<String, String> cpuCoolerCharacteristicMap = new HashMap<>() {{
        put("noiseLevel", "Уровень шума, дБ");
        put("backlight", "Подсветка");
        put("mountType", "Тип крепления");
        put("supportedSockets", "Поддерживаемые сокеты");
        put("thermalPaste", "Термопаста");
        put("fanCount", "Количество вентиляторов в комплекте, шт");
        put("height", "Высота, мм");
        put("width", "Ширина, мм");
        put("length", "Глубина, мм");
        put("weight", "Вес, гр");
        put("voltage", "Напряжение питания, В");
        put("connector", "Коннектор");
        put("tdp", "Рассеиваемая мощность TDP, Вт");
        put("coolerHeight", "Высота кулера, мм");
        put("radiatorMaterial", "Материал радиатора");
        put("baseMaterial", "Материал основания");
        put("coolerDesign", "Конструкция кулера");
        put("isRotationSpeedAdjustable", "Регулировка скорости вращения");
        put("fanBearingType", "Тип подшипника вентилятора");
        put("cfm", "Воздушный поток, CFM");
        put("rotationalSpeed", "Скорость вращения");
        put("fanDimensions", "Размеры вентилятора");
        put("coolingSystemDesign", "Конструкция системы охлаждения");
    }};

    public List<CPUCooler> scrapeCPUCoolers() {
        List<CPUCooler> cpuCoolers = technoDomScraper
                .getComponentItems(TechnoDomScraper.COMPONENTS_BASE_URI + "/kulery-dlja-processorov", cpuCoolerCharacteristicMap)
                .stream()
                .peek(cpuCoolerMap -> {
                })
                .map(cpuCoolerMap -> objectMapper.convertValue(cpuCoolerMap, CPUCooler.class))
                .toList();
        cpuCoolers.forEach(System.out::println);
        return cpuCoolers;
    }
}
