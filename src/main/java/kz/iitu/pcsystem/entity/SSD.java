package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)

@JsonIgnoreProperties(ignoreUnknown = true)
public class SSD extends ComponentEntity {
    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(name = "form_factor")
    private String formFactor;

    @Column(name = "interface_type")
    private String interfaceType;

    @Column(name = "capacity_gb")
    private String capacityGB;

    @Column
    private String tbw;

    @Column(name = "read_speed_mbps")
    private String readSpeedMBps;

    @Column(name = "write_speed_mbps")
    private String writeSpeedMBps;

    @Column
    private String mtbf;

    @Column
    private String dimensions;

    @Override
    public void setId() {
        setId(manufacturer + " " + model);
    }
}
