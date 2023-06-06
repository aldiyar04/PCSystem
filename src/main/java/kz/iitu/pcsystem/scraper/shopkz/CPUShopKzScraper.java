package kz.iitu.pcsystem.scraper.shopkz;

import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.util.Util;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CPUShopKzScraper extends ShopKzScraper<CPU> {
    private static final Map<String, String> cpuCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("cpuType", "Тип процессора");
        put("model", "Модель");
        put("socket", "Сокет");
        put("coreCount", "Общее количество ядер");
        put("threadCount", "Количество потоков");
        put("coreClock", "Тактовая частота, ГГц");
        put("boostCoreClock", "Максимальная тактовая частота, ГГц");
        put("cacheL1", "Объем кэша L1");
        put("cacheL2", "Объем кэша L2");
        put("cacheL3", "Объем кэша L3");
        put("supportedMemoryTypes", "Тип поддерживаемой памяти");
        put("maxMemory", "Максимальный объем памяти");
        put("isEccMemorySupported", "Поддержка памяти ECC");
        put("integratedGraphics", "Интегрированная графическая система");
        put("lithography", "Техпроцесс");
        put("tdp", "Расчетная мощность (TDP)");
        put("maxTdp", "Максимальная расчетная мощность (TDP)");
        put("technologies", "Поддерживаемые технологии");
        put("instructions", "Поддерживаемые инструкции");
        put("criticalTemperature", "Критическая температура");
    }};

    @Override
    public List<ComponentProduct<CPU>> scrape() {
        return scrapeComponentItems("protsessory", cpuCharacteristicMap, CPU.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> cpuCharacteristicMap) {
        Map<String, String> result = new HashMap<>(cpuCharacteristicMap);

        if ("Intel".equals(result.get("manufacturer")) && result.get("cpuType").contains("Core i")) {
            result.put("model", result.get("cpuType") + "-" + result.get("model"));
        } else {
            result.put("model", result.get("cpuType") + " " + result.get("model"));
        }
        result.remove("cpuType");

        String maxMemory = result.get("maxMemory");
        if (maxMemory != null)
            result.put("maxMemory", maxMemory.split(" ")[0]); // example: 128 Гб

        Util.mapBooleanField(result, "isEccMemorySupported");

        String lithography = result.get("lithography");
        if (lithography != null)
            result.put("lithography", lithography.split(" ")[0]); // example: 7 нм

        String tdp = result.get("tdp");
        if (tdp != null)
            result.put("tdp", tdp.split(" ")[0]); // example: 65 Вт

        String criticalTemperature = result.get("criticalTemperature");
        if (criticalTemperature != null)
            result.put("criticalTemperature", criticalTemperature.split("°")[0]); // example: 90°C

        return result;
    }
}
