package project.laptop.web;


import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.laptop.model.dto.userDTO.UserRegisterDto;
import project.laptop.service.UserService;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {
    private final UserService userService;
    private  LocaleResolver localeResolver;


    public UserRegistrationController(UserService userService ,LocaleResolver localeResolver ) {
        this.userService = userService;


        this.localeResolver = localeResolver;
    }

    @GetMapping("/register")
    public String register(){
        return "auth-register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDto userRegisterDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  HttpServletRequest request) throws MessagingException {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterDto",userRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto",bindingResult);
            return "redirect:register";
        }
        userService.registerAndLogin(userRegisterDto,localeResolver.resolveLocale(request));

        return "redirect:/";
    }
    @ModelAttribute
    public UserRegisterDto userRegisterDto(){
        return new UserRegisterDto();
    }

}
