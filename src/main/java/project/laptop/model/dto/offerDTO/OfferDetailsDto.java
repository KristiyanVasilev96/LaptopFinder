package project.laptop.model.dto.offerDTO;

import project.laptop.model.entity.enums.ConditionEnum;
import project.laptop.model.entity.enums.OperationSystem;

import java.math.BigDecimal;

public class OfferDetailsDto {
    private Long id;
    private String imageUrl;
    private BigDecimal weight;
    private String brand;
    private String model;
    private Integer ram;
    private BigDecimal price;
    private ConditionEnum laptopCondition;
    private OperationSystem operationSystem;

    private String description;

    private String sellerFirstName;
    private String sellerLastName;

    public OfferDetailsDto() {
    }

    public Long getId() {
        return id;
    }

    public OfferDetailsDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferDetailsDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public OfferDetailsDto setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public OfferDetailsDto setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferDetailsDto setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getRam() {
        return ram;
    }

    public OfferDetailsDto setRam(Integer ram) {
        this.ram = ram;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDetailsDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ConditionEnum getLaptopCondition() {
        return laptopCondition;
    }

    public OfferDetailsDto setLaptopCondition(ConditionEnum laptopCondition) {
        this.laptopCondition = laptopCondition;
        return this;
    }

    public OperationSystem getOperationSystem() {
        return operationSystem;
    }

    public OfferDetailsDto setOperationSystem(OperationSystem operationSystem) {
        this.operationSystem = operationSystem;
        return this;
    }

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public OfferDetailsDto setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
        return this;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public OfferDetailsDto setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
        return this;
    }
    public String getSellerFullName(){
        return sellerFirstName+" "+sellerLastName;
    }
    public String getOfferHighlight() {
        return this.brand + " " + this.model;
    }

    public String getDescription() {
        return description;
    }

    public OfferDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
