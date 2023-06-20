package project.laptop.web;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.laptop.model.dto.userDTO.UpdateEmailDTO;
import project.laptop.model.dto.userDTO.UserRoleDto;
import project.laptop.model.entity.UserEntity;
import project.laptop.repository.UserRepository;
import project.laptop.repository.UserRoleRepository;
import project.laptop.service.UserService;


import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes
    ) {


        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);


        return "redirect:/users/login";
    }

    @GetMapping("/profile") //profile view
    public String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        UserEntity user = userService.findByEmail(email);
        String role=userService.getUserRole(email);


        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("email", email);
        model.addAttribute("role", role);

        return "profile";
    }





    @GetMapping("/edit-profile")
    public String edit(Model model, Principal principal) {
        String currentEmail = userService.findByEmail(principal.getName()).getEmail();
        model.addAttribute("currentEmail", currentEmail);

        return "edit-profile";
    }

    @PostMapping("/edit-profile") //change email
    public String updateEmail(@Valid UpdateEmailDTO updateEmailDTO,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes,
                              Model model) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateEmailDTO", updateEmailDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateEmailDTO", bindingResult);

            return "redirect:edit-profile";
        }

        userService.updateEmail(updateEmailDTO);
        model.addAttribute("email", updateEmailDTO.getEmail());

        return "redirect:profile";
    }

    @GetMapping("/change-role")
    public String changeUserRole(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "change-role";
    }

    @PostMapping("/change-role")
    public String changeUserRoleConfirm(@Valid UserRoleDto userRoleDto,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes
                                        ) {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRoleDto",userRoleDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRoleDto",bindingResult);

            return "redirect:change-role";
        }

        userService.changeUserRole(userRoleDto);
        return "redirect:profile";
    }









    @ModelAttribute
    public UpdateEmailDTO updateEmailDTO() {
        return new UpdateEmailDTO();
    }

    @ModelAttribute
    public UserRoleDto userRoleDto(){
        return new UserRoleDto();
    }

}

