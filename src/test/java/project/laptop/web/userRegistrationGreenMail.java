//package project.laptop.web;
//
//import com.icegreen.greenmail.util.GreenMail;
//import com.icegreen.greenmail.util.ServerSetup;
//import jakarta.mail.internet.MimeMessage;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import project.laptop.model.entity.UserRoleEntity;
//import project.laptop.model.entity.enums.UserRoleEnum;
//import project.laptop.repository.UserRoleRepository;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class userRegistrationGreenMail {
//
//    @Value("${mail.host}")
//    private String mailHost;
//    @Value("${mail.port}")
//    private Integer mailPort;
//    @Value("${mail.username}")
//    private String userName;
//    @Value("${mail.password}")
//    private String password;
//
//    private GreenMail greenMail;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserRoleRepository userRoleRepository;
//
//    @BeforeEach
//    void setUp() {
//        greenMail = new GreenMail(new ServerSetup(mailPort, mailHost, "smtp"));
//        greenMail.start();
//        greenMail.setUser(userName, password);
//    }
//
//    @AfterEach
//    void tearDown() {
//        greenMail.stop();
//    }
//
//    @Test
//    void testRegistration() throws Exception {
//        UserRoleEntity userRoleEntity = new UserRoleEntity(UserRoleEnum.USER);
//        userRoleRepository.save(userRoleEntity);
//
//        mockMvc.perform(post("/users/register").
//                        param("email", "anna@example.com").
//                        param("firstName", "Anna").
//                        param("lastName", "Petrova").
//                        param("password", "topsecret").
//                        param("confirmPassword", "topsecret").
//                        with(csrf())
//                ).
//                andExpect(status().is3xxRedirection()).
//                andExpect(redirectedUrl("/"));
//
//        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
//        Assertions.assertEquals(1, receivedMessages.length);
//
//        MimeMessage welcomeMessage = receivedMessages[0];
//
//        Assertions.assertTrue(welcomeMessage.getContent().toString().
//                contains("Anna Petrova"));
//    }
//}
