package project.laptop.service;

import org.springframework.stereotype.Service;
import project.laptop.model.dto.brandDto.BrandDto;
import project.laptop.model.dto.modelDTO.ModelDTO;
import project.laptop.model.entity.BrandEntity;
import project.laptop.model.entity.ModelEntity;
import project.laptop.repository.BrandRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {
    private BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandDto> getAllBrands() {
        return brandRepository.
                findAll().
                stream().
                map(this::mapBrand).
                collect(Collectors.toList());
    }

    private BrandDto mapBrand(BrandEntity brandEntity) {
        List<ModelDTO> models = brandEntity.
                getModels().
                stream().
                map(this::mapModel).
                toList();

        return new BrandDto().
                setModels(models).
                setName(brandEntity.getName());
    }

    private ModelDTO mapModel(ModelEntity modelEntity) {
        return new ModelDTO().
                setId(modelEntity.getId()).
                setName(modelEntity.getName());
    }
}
