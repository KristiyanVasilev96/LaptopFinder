package project.laptop.service;

import org.springframework.stereotype.Service;
import project.laptop.model.entity.CartEntity;
import project.laptop.model.entity.OfferEntity;
import project.laptop.repository.CartRepository;
import project.laptop.repository.OfferRepository;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final OfferService offerService;

    private final OfferRepository offerRepository;

    public CartService(CartRepository cartRepository, OfferService offerService, OfferRepository offerRepository) {
        this.cartRepository = cartRepository;
        this.offerService = offerService;
        this.offerRepository = offerRepository;
    }

    public void save(CartEntity cart) {
        cartRepository.save(cart);
    }


    public void addOfferToCart(CartEntity cart, OfferEntity offer) {
        offer.setCart(cart);
        cart.getOffers().add(offer);
    }

    public void buyAllCartOffers(CartEntity cart) {
        for (OfferEntity offer : cart.getOffers()) {
            offerService.deleteOfferById(offer.getId());
        }
        cart.getOffers().clear();
    }

    public double getTotalPrice(CartEntity cart) {

        return cart.getOffers().stream().mapToDouble(o -> o.getPrice().doubleValue()).sum();
    }


    public void removeOfferFromCart(CartEntity cart, Long offerId) {
        // Retrieve the offer to remove by its ID
        Optional<OfferEntity> offerOptional = offerRepository.findById(offerId);
        if (offerOptional.isPresent()) {
            OfferEntity offerToRemove = offerOptional.get();

            // Check if the offer is already in the cart
            if (cart.getOffers().contains(offerToRemove)) {
                // Remove the offer from the cart
                cart.getOffers().remove(offerToRemove);
                offerToRemove.setCart(null); // Set the offer's cart ID to null
            }
        }
    }

    public int getCartItemCount(CartEntity cart) {
        if (cart != null && cart.getOffers() != null) {
            return cart.getOffers().size();
        }
        return 0;
    }
}
