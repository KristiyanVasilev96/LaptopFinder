package project.laptop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import project.laptop.model.entity.CartEntity;
import project.laptop.model.entity.UserEntity;
import project.laptop.service.CartService;
import project.laptop.service.UserService;

import java.security.Principal;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    private final CartService cartService;
    private final UserService userService;

    @Autowired
    public ControllerAdvice(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }


    @ModelAttribute
    public void addCartAttributes(Model model, Principal principal) {
        if (principal != null) {
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
            model.addAttribute("cartCount", cartCount);
        }
    }
}
