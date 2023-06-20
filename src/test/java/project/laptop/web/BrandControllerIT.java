package project.laptop.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BrandControllerIT {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void testBrandPage() throws Exception {
        mockMvc.perform(get("/brands/all")).
                andExpect(status().isOk()).
                andExpect(view().name("brands"));
    }

    @Test
    void maintenancePage() throws Exception {
        mockMvc.perform(get("/maintenance"))
                .andExpect(status().isOk())
                .andExpect(view().name("maintenance"));
    }
}