package project.laptop.model.entity;

import jakarta.persistence.*;
import project.laptop.model.entity.enums.ConditionEnum;
import project.laptop.model.entity.enums.OperationSystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {
    @Column(nullable = false)
    private BigDecimal weight;
    @Enumerated(EnumType.STRING)
    @Column(name = "laptop_condition",nullable = false)
    private ConditionEnum laptopCondition;
    @Column(nullable = false)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private OperationSystem operationSystem;
    @Column(nullable = false)
    private Integer ram;
    @Column(updatable = true)
    private String imageUrl;
    @Column
    private String description;
    @ManyToOne
    private ModelEntity model;
    @ManyToOne
    private UserEntity seller;

    @OneToMany(mappedBy = "offer",cascade = CascadeType.ALL)
    private List<CommentEntity> comments=new ArrayList<>();

    @ManyToOne
    private CartEntity cart;
    public OfferEntity() {
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public OfferEntity setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public ConditionEnum getLaptopCondition() {
        return laptopCondition;
    }

    public OfferEntity setLaptopCondition(ConditionEnum conditionEnum) {
        this.laptopCondition = conditionEnum;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public OperationSystem getOperationSystem() {
        return operationSystem;
    }

    public OfferEntity setOperationSystem(OperationSystem operationSystem) {
        this.operationSystem = operationSystem;
        return this;
    }

    public Integer getRam() {
        return ram;
    }

    public OfferEntity setRam(Integer ram) {
        this.ram = ram;
        return this;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public OfferEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public ModelEntity getModel() {
        return model;
    }

    public OfferEntity setModel(ModelEntity model) {
        this.model = model;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public CartEntity getCart() {
        return cart;
    }

    public OfferEntity setCart(CartEntity cart) {
        this.cart = cart;
        return this;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public OfferEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
