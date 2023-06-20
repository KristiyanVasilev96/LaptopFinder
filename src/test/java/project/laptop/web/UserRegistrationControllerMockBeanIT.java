package project.laptop.web;

import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import project.laptop.model.entity.UserRoleEntity;
import project.laptop.model.entity.enums.UserRoleEnum;
import project.laptop.repository.UserRepository;
import project.laptop.repository.UserRoleRepository;
import project.laptop.service.EmailService;
import project.laptop.service.UserService;


import java.util.Locale;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerMockBeanIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmailService mockEmailService;


    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/users/register")).
                andExpect(status().isOk()).
                andExpect(view().name("auth-register"));
    }


    @Test
    void testUserRegistration() throws Exception {

        UserRoleEntity userRoleEntity = new UserRoleEntity(UserRoleEnum.USER);
        userRoleRepository.save(userRoleEntity);

        mockMvc.perform(post("/users/register").
                        param("email", "kris@example.com").
                        param("firstName", "Kristiyan").
                        param("lastName", "Vasilev").
                        param("password", "12345").
                        param("confirmPassword", "12345").

                        cookie(new Cookie("lang", Locale.GERMAN.getLanguage())).
                        with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));

        verify(mockEmailService).
                sendRegistrationEmail("kris@example.com",
                        "Kristiyan Vasilev",
                        Locale.GERMAN);
    }

    @Test
    void testUserRegistrationFail() throws Exception {
        mockMvc.perform(post("/users/register").
                        param("email", "kris.com").
                        param("firstName", "Kristiyan").
                        param("lastName", "Vasilev").
                        param("password", "12345").
                        param("confirmPassword", "12345").
                        cookie(new Cookie("lang", Locale.GERMAN.getLanguage())).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("register"));


    }


}
