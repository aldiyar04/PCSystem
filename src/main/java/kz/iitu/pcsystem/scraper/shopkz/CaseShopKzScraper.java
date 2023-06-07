package kz.iitu.pcsystem.scraper.shopkz;

import kz.iitu.pcsystem.entity.Case;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CaseShopKzScraper extends ShopKzScraper<Case> {
    private static final Map<String, String> caseCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("model", "Модель");
        put("caseFormFactor", "Форм-фактор корпуса");
        put("compatibleFormFactors", "Форм-фактор совместимых плат");
        put("material", "Материал");
        put("frontPanelConstruction", "Конструкция передней панели");
        put("builtInPowerSupply", "Встроенный блок питания");
        put("powerSupplyPosition", "Расположение блока питания");
        put("hddMounting", "Крепление HDD");
        put("internal2_5InchDriveBays", "Количество внутренних отсеков 2.5\\\"");
        put("internal3_5InchDriveBays", "Количество внутренних отсеков 3.5\\\"");
        put("buttons", "Кнопки");
        put("indicators", "Индикаторы");
        put("expansionSlotsCount", "Количество слотов расширения");
        put("graphicsCardInstallationType", "Тип установки видеокарты");
        put("maxGraphicsCardLength", "Максимальная длина видеокарты, мм");
        put("maxCpuCoolerHeight", "Максимальная высота процессорного кулера, мм");
        put("includedCooling", "Установленное охлаждение");
        put("supportedFanSizes", "Поддерживаемые диаметры вентиляторов");
        put("maxLiquidCoolerLength", "Максимальная длина радиатора СВО, мм");
        put("supportedLiquidCoolerLocations", "Места для монтажа радиатора СВО");
        put("dustFilters", "Противопылевой фильтр");
        put("additionalConnectors", "Дополнительные разъемы");
        put("color", "Цвет, используемый в оформлении");
        put("dimensions", "Размеры (Ш х В х Г) ");
    }};

    @Override
    public List<ComponentProduct<Case>> scrape() {
        return scrapeComponentItems("korpusa", caseCharacteristicMap, Case.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap) {
        return componentItemCharacteristicMap;
    }
}
