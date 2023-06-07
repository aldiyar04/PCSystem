package kz.iitu.pcsystem.scraper.shopkz;

import kz.iitu.pcsystem.entity.CPUCooler;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CPUCoolerShopKzScraper extends ShopKzScraper<CPUCooler> {
    private static final Map<String, String> cpuCoolerCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("model", "Модель");
        put("type", "Тип");
        put("socket", "Сокет");
        put("maxTdp", "Максимальный TDP процессора, Вт");
        put("material", "Материал");
        put("fanDiameter", "Диаметр вентилятора, мм");
        put("minRotationSpeed", "Минимальная скорость вращения, обор./мин.");
        put("maxRotationSpeed", "Максимальная скорость вращения, обор./мин.");
        put("rotationSpeedAdjustment", "Возможность регулирования оборотов");
        put("maxNoiseLevel", "Максимальный уровень шума, дБ");
        put("height", "Высота кулера, мм");
        put("connector", "Коннектор");
        put("airflow", "Воздушный поток");
        put("bearingType", "Тип подшипника");
        put("heatPipesCount", "Количество тепловых трубок");
        put("powerConsumption", "Потребление энергии, Вт");
        put("powerVoltage", "Напряжение питания");
        put("lighting", "Подсветка");
        put("dimensions", "Размеры (Ш х В х Г)");
    }};

    @Override
    public List<ComponentProduct<CPUCooler>> scrape() {
        return scrapeComponentItems("kulery-dlya-protsessora", cpuCoolerCharacteristicMap, CPUCooler.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap) {
        return componentItemCharacteristicMap;
    }
}
