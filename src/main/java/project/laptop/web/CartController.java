package project.laptop.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.laptop.model.entity.CartEntity;
import project.laptop.model.entity.OfferEntity;
import project.laptop.model.entity.UserEntity;
import project.laptop.service.CartService;
import project.laptop.service.OfferService;
import project.laptop.service.UserService;

import java.security.Principal;

@Controller
public class CartController {
    private final UserService userService;
    private final OfferService offerService;
    private final CartService cartService;



    public CartController(UserService userService, OfferService offerService, CartService cartService) {
        this.userService = userService;
        this.offerService = offerService;
        this.cartService = cartService;
    }


    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        UserEntity currentUser = userService.findByEmail(principal.getName());
        CartEntity cart = currentUser.getCart();
        if (cart == null) {
            cart = new CartEntity();
            cart.setBuyer(currentUser);
            currentUser.setCart(cart);
        }
        int cartCount = cartService.getCartItemCount(cart);
        double totalPrice = cartService.getTotalPrice(cart);

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cart);
        model.addAttribute("cartCount", cartCount);

        return "cart";
    }



    @PostMapping("/offers/{id}/buy")
    public String buyOffer(@PathVariable("id") Long offerId, Principal principal) {
        UserEntity currentUser = userService.findByEmail(principal.getName());
        OfferEntity offer = offerService.findById(offerId);
        CartEntity cart = currentUser.getCart();
        if (cart == null) {
            cart = new CartEntity();
            cart.setBuyer(currentUser);
            currentUser.setCart(cart);
        }
        cartService.addOfferToCart(cart, offer);
        cartService.save(cart);
        return "redirect:/offers/all";
    }

    @DeleteMapping("/cart/buyAll")
    public String buyAll(Model model, Principal principal) {
        UserEntity currentUser = userService.findByEmail(principal.getName());
        CartEntity cart = currentUser.getCart();
        if (cart != null) {
            cartService.buyAllCartOffers(cart);
            cartService.save(cart);
        }
        return "redirect:/cart/success";
    }


    @PostMapping("/cart/{offerId}/remove")
    public String removeOfferFromCart(@PathVariable("offerId") Long offerId, Principal principal) {
        UserEntity currentUser = userService.findByEmail(principal.getName());
        CartEntity cart = currentUser.getCart();
        if (cart != null) {
            cartService.removeOfferFromCart(cart, offerId);
            cartService.save(cart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/success")
    public String orderSuccess() {

        return "cart-success";
    }





}
