package kz.iitu.pcsystem.pojo;

import kz.iitu.pcsystem.entity.Component;
import kz.iitu.pcsystem.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ComponentProduct<T extends Component> {
    private T component;
    private final Product product;
}
