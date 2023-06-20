package project.laptop.model.dto.offerDTO;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;
import project.laptop.model.entity.enums.ConditionEnum;
import project.laptop.model.entity.enums.OperationSystem;

import java.math.BigDecimal;

public class CreateOrUpdateOfferDto {
    @NotNull(message = "Please select Model!")
    @Min(1)
    private Long modelId;
    @NotNull(message = "Please select Condition!")
    private ConditionEnum laptopCondition;
    @NotNull(message = "Please select Operation System!")
    private OperationSystem operationSystem;
    @Positive(message = "Suggested price must be positive!")
    @NotNull(message = "Please enter price!")
    private BigDecimal price;
    @Min(4)
    @NotNull(message = "Ram specifications are required!")
    private Integer ram;
    @Positive(message = "Weight in kg is Required!")
    @NotNull(message = "Please enter weight!")
    private BigDecimal weight;
    @NotEmpty(message = "Please enter description!")
    private String description;
//    @NotBlank(message = "Please enter Image URL!")
    private MultipartFile imageUrl;

//    private MultipartFile imageUrl;
    public CreateOrUpdateOfferDto() {
    }

    public Long getModelId() {
        return modelId;
    }

    public CreateOrUpdateOfferDto setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public ConditionEnum getLaptopCondition() {
        return laptopCondition;
    }

    public CreateOrUpdateOfferDto setLaptopCondition(ConditionEnum laptopCondition) {
        this.laptopCondition = laptopCondition;
        return this;
    }

    public OperationSystem getOperationSystem() {
        return operationSystem;
    }

    public CreateOrUpdateOfferDto setOperationSystem(OperationSystem operationSystem) {
        this.operationSystem = operationSystem;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateOrUpdateOfferDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getRam() {
        return ram;
    }

    public CreateOrUpdateOfferDto setRam(Integer ram) {
        this.ram = ram;
        return this;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public CreateOrUpdateOfferDto setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateOrUpdateOfferDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public CreateOrUpdateOfferDto setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
