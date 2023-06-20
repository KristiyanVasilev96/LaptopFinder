package project.laptop.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class CartEntity extends BaseEntity{
    @OneToMany(mappedBy = "cart")
    private List<OfferEntity> offers = new ArrayList<>();
    @OneToOne
    private UserEntity buyer;

    public CartEntity() {
    }

    public List<OfferEntity> getOffers() {
        return offers;
    }

    public CartEntity setOffers(List<OfferEntity> offers) {
        this.offers = offers;
        return this;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public CartEntity setBuyer(UserEntity buyer) {
        this.buyer = buyer;
        return this;
    }
}
