package kz.iitu.pcsystem.scraper.technodom;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.pcsystem.entity.PowerSupply;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class PowerSupplyScraper {
    private final TechnoDomScraper technoDomScraper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Map<String, String> powerSupplyCharacteristicMap = new HashMap<>() {{
        put("height", "Высота, см");
        put("width", "Ширина, см");
        put("length", "Глубина, см");
        put("formFactor", "Форм-фактор");
        put("power", "Мощность, Вт");
        put("frequency", "Частота");
        put("inputVoltage", "Входное напряжение");
        put("securityFeatures", "Безопасность");
        put("certificate", "Сертификат");
        put("fanDiameter", "Диаметр вентилятора, мм");
        put("pfcPresence", "Наличие PFC");
        put("isRgbBacklight", "Подсветка RGB");
        put("efficiencyPercentage", "Производительность (КПД), %");
        put("coolingSystem", "Система охлаждения");
        put("powerSupplyManagement", "Управление блоком питания");
        put("canDisconnectCables", "Возможность отсоединения кабелей");
        put("cableLength", "Длина кабеля питания, м");
        put("cpuPowerConnector", "Коннектор питания процессора");
        put("sataConnectorCount", "Количество SATA коннекторов, шт");
        put("molexConnectorCount", "Количество Molex коннекторов (4-pin), шт");
        put("videoCardPowerConnector", "Коннектор питания видеокарты");
        put("floppyConnectorCount", "Количество Floppy коннекторов (4-pin), шт");
        put("motherboardPowerConnector", "Коннектор питания материнской платы");
    }};

    public List<PowerSupply> scrapePowerSupplies() {
        List<PowerSupply> powerSupplies = technoDomScraper
                .getComponentItems(TechnoDomScraper.COMPONENTS_BASE_URI + "/bloki-pitanija", powerSupplyCharacteristicMap)
                .stream()
                .peek(powerSupplyMap -> {
                })
                .map(powerSupplyMap -> objectMapper.convertValue(powerSupplyMap, PowerSupply.class))
                .toList();
        powerSupplies.forEach(System.out::println);
        return powerSupplies;
    }
}
