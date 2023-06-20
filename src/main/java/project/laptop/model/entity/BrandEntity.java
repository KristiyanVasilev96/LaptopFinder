package project.laptop.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ModelEntity> models=new ArrayList<>();

    public BrandEntity() {
    }

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelEntity> getModels() {
        return models;
    }

    public BrandEntity setModels(List<ModelEntity> models) {
        this.models = models;
        return this;
    }
}
