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

    public static final String COMPONENT_TYPE_CPU = "cpu";
    public static final String COMPONENT_TYPE_MOTHERBOARD = "motherboard";
    public static final String COMPONENT_TYPE_CPU_COOLER = "cpuCooler";
    public static final String COMPONENT_TYPE_VIDEO_CARD = "videoCard";
    public static final String COMPONENT_TYPE_MEMORY = "memory";
    public static final String COMPONENT_TYPE_SSD = "ssd";
    public static final String COMPONENT_TYPE_HDD = "hdd";
    public static final String COMPONENT_TYPE_POWER_SUPPLY = "powerSupply";
    public static final String COMPONENT_TYPE_CASE = "casee";
}
