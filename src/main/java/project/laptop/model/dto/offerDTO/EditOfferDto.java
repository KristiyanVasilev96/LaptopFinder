package project.laptop.model.dto.offerDTO;

import jakarta.validation.constraints.*;
import project.laptop.model.entity.enums.ConditionEnum;
import project.laptop.model.entity.enums.OperationSystem;

import java.math.BigDecimal;

public class EditOfferDto {
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
    @NotBlank(message = "Please enter Image URL!")
    private String imageUrl;

    public EditOfferDto() {
    }

    public ConditionEnum getLaptopCondition() {
        return laptopCondition;
    }

    public EditOfferDto setLaptopCondition(ConditionEnum laptopCondition) {
        this.laptopCondition = laptopCondition;
        return this;
    }

    public OperationSystem getOperationSystem() {
        return operationSystem;
    }

    public EditOfferDto setOperationSystem(OperationSystem operationSystem) {
        this.operationSystem = operationSystem;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public EditOfferDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getRam() {
        return ram;
    }

    public EditOfferDto setRam(Integer ram) {
        this.ram = ram;
        return this;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public EditOfferDto setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EditOfferDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public EditOfferDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
