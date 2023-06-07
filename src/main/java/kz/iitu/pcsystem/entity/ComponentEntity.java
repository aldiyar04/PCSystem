package kz.iitu.pcsystem.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@SuperBuilder
@NoArgsConstructor
@Data
public abstract class ComponentEntity {
    @Id
    @Setter(AccessLevel.PACKAGE)
    private String id;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "component")
    private List<Product> products = new ArrayList<>();

    @Column(nullable = false)
    private String imageUri;

    public abstract void setId();

    public String getId() {
        if (id == null) setId();
        return id;
    }

    public void addProduct(Product product) {
        product.setComponent(this);
        System.out.println("PRODUCT: " + product);
        products.add(product);
    }

    public static final String COMPONENT_TYPE_CPU = "CPU";
    public static final String COMPONENT_TYPE_MOTHERBOARD = "MOTHERBOARD";
    public static final String COMPONENT_TYPE_CPU_COOLER = "CPU_COOLER";
    public static final String COMPONENT_TYPE_VIDEO_CARD = "VIDEO_CARD";
    public static final String COMPONENT_TYPE_MEMORY = "MEMORY";
    public static final String COMPONENT_TYPE_SSD = "SSD";
    public static final String COMPONENT_TYPE_HDD = "HDD";
    public static final String COMPONENT_TYPE_POWER_SUPPLY = "POWER_SUPPLY";
    public static final String COMPONENT_TYPE_CASE = "CASEE";
}
