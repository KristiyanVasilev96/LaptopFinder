package project.laptop.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import project.laptop.model.entity.OfferEntity;
import project.laptop.model.entity.UserEntity;
import project.laptop.util.TestDataUtil;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerIT {

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



    @Test
    void testDeleteByAnonymousUser_Forbidden() throws Exception {
        mockMvc.perform(delete("/offers/{id}", testOffer.getId()).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection());
        //TODO: check redirection url to login w/o schema
    }

    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testDeleteByAdmin() throws Exception {
        mockMvc.perform(delete("/offers/{id}", testOffer.getId()).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:all"));
    }

    @WithMockUser(
            username = "user@example.com",
            roles = "USER"
    )
    @Test
    void testDeleteByOwner() throws Exception {
        mockMvc.perform(delete("/offers/{id}", testOffer.getId()).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:all"));
    }

    @WithMockUser(
            username = "user@example.com",
            roles = "USER"
    )
    @Test
    public void testDeleteNotOwned_Forbidden() throws Exception {
        mockMvc.perform(delete("/offers/{id}", testAdminOffer.getId()).
                        with(csrf())
                ).
                andExpect(status().isForbidden());
    }


    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    @Test
    void testAddOfferView() throws Exception {

        mockMvc.perform(get("/offers/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("offer-add"));
    }

    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    @Test
    void testAddOffer() throws Exception {

        mockMvc.perform(post("/offers/add").
                        param("modelId", "1").
                        param("price", "11200").
                        param("operationSystem", "Windows_10").
                        param("ram", "8").
                        param("weight", "2").
                        param("description", "test").
                        param("laptopCondition", "EXCELLENT").
                        param("imageUrl", "image://test.png").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("all"));
    }

    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    @Test
    void testAddOfferFail() throws Exception {

        mockMvc.perform(post("/offers/add").
                        param("modelId", "1").
                        // Set an invalid price value
                                param("price", "0").
                        param("operationSystem", "Windows_10").
                        // Set an invalid RAM value
                                param("ram", "-2").
                        param("weight", "2").
                        param("description", "test").
                        param("laptopCondition", "EXCELLENT").
                        // Set an invalid image URL
                                param("imageUrl", "invalid_image_url").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/offers/add"));
    }



    @Test
    void testSearch() throws Exception {
        mockMvc.perform(get("/offers/search"))
                .andExpect(status().isOk());

    }

    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    @Test
    public void TestEditViewByOwner() throws Exception {
        mockMvc.perform(get("/offers/{id}/edit",testOffer.getId())
                .with(csrf())
                ).andExpect(status().isOk())
                .andExpect(view().name("update"));

    }

    @Test
    @WithMockUser(
            username = "user@example.com",
            roles = "USER"
    )
    public void editTestSuccess() throws Exception {
        mockMvc.perform(post("/offers/{id}/edit",testOffer.getId()
                        ).param("price","2000")
                        .param("ram","24")
                        .param("weight","2")
                        .param("imageUrl","https://image-example.com")
                        .param("operationSystem","Linux")
                        .param("laptopCondition","EXCELLENT")
                        .param("description","wtdfffff")
                .with(csrf())).
        andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/offers/"+testOffer.getId()));
    }
    @Test
    @WithMockUser(
            username = "user@example.com",
            roles = "USER"
    )
    public void editFail() throws Exception {
        mockMvc.perform(post("/offers/{id}/edit",testOffer.getId()
                ).param("price","2000")
                        .param("ram","0")
                        .param("weight","2")
                        .param("imageUrl","no-image")
                        .param("operationSystem","Linux")
                        .param("laptopCondition","EXCELLENT")
                        .param("description","wtdfffff")
                        .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/offers/{id}/edit"));
    }


}
