package project.laptop.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import project.laptop.model.entity.OfferEntity;
import project.laptop.model.entity.UserEntity;
import project.laptop.util.TestDataUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtils;


    private UserEntity testUser, testAdmin;

    private OfferEntity testOffer, testAdminOffer;

    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("user@example.com");
        testAdmin = testDataUtils.createTestAdmin("admin@example.com");
        var testModel =
                testDataUtils.createTestModel(testDataUtils.createTestBrand());

        testOffer = testDataUtils.createTestOffer(testUser, testModel);
        testAdminOffer = testDataUtils.createTestOffer(testAdmin, testModel);
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

//    @WithUserDetails(value = "user@example.com",
//            userDetailsServiceBeanName = "testUserDataService")
//    @Test
//    void testCartView() throws Exception {
//        mockMvc.perform(get("/cart"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("cart"));
//    }
@WithUserDetails(value = "user@example.com",
        userDetailsServiceBeanName = "testUserDataService")
    @Test
    void testCartView() throws Exception {

        UserEntity user = new UserEntity();
        user.setEmail("test@example.com");

        mockMvc.perform(get("/cart"))
                .andExpect(status().isOk())
                .andExpect(view().name("cart"))
                .andExpect(model().attributeExists("cart"))
                .andExpect(model().attributeExists("cartCount"))
                .andExpect(model().attributeExists("totalPrice"))
                .andExpect(model().attribute("cartCount", 0))
                .andExpect(model().attribute("totalPrice", 0.0));
    }



}
