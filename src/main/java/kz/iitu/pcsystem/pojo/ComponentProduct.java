package kz.iitu.pcsystem.pojo;

import kz.iitu.pcsystem.entity.ComponentEntity;
import kz.iitu.pcsystem.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ComponentProduct<T extends ComponentEntity> {
    private T component;
    private final Product product;
}
