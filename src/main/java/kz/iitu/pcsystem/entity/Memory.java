package kz.iitu.pcsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)

@JsonIgnoreProperties(ignoreUnknown = true)
public class Memory extends ComponentEntity {
    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false, name = "memory_type")
    private String memoryType;

    @Column(nullable = false, name = "memory_size")
    private String memorySize;

    @Column(name = "memory_frequency")
    private String memoryFrequency;

    @Column(name = "memory_bandwidth")
    private String memoryBandwidth;

    @Column
    private String voltage;

    @Column
    private String timings;

    @Column
    private String features;

    @Column
    private String height;

    @Override
    public void setId() {
        String id = manufacturer + "_" + memoryType + "_" + memorySize;
        setId(id.replaceAll("\\s", "_").replaceAll("/", "--"));
    }
}
