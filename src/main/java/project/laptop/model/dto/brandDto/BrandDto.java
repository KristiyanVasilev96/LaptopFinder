package project.laptop.model.dto.brandDto;

import project.laptop.model.dto.modelDTO.ModelDTO;

import java.util.ArrayList;
import java.util.List;

public class BrandDto {
    private String name;
    private List<ModelDTO> models;

    public BrandDto() {
    }

    public String getName() {
        return name;
    }

    public BrandDto setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelDTO> getModels() {
        return models;
    }

    public BrandDto setModels(List<ModelDTO> models) {
        this.models = models;
        return this;
    }

    public BrandDto addModel(ModelDTO model) {
        if (this.models == null) {
            this.models = new ArrayList<>();
        }
        this.models.add(model);
        return this;
    }
}
