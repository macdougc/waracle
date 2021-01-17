package com.waracle.cakemgr;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.waracle.cakemgr.controllers.DefaultController;
import com.waracle.cakemgr.entities.Cake;
import com.waracle.cakemgr.entities.CakeRepository;
import com.waracle.cakemgr.services.CakeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@ContextConfiguration(classes=CakeMgrApplication.class)
@AutoConfigureMockMvc
@WebMvcTest(DefaultController.class)
public class DefaultControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CakeService _cakeServiceMock;

    @MockBean
    private CakeRepository _cakeRepositoryMock;

    @Test
    public void testRootPage() throws Exception {
        this.mockMvc.perform(
                get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("cakeList"));
    };

    @Test
    public void testAddCakePage() throws Exception {
        this.mockMvc.perform(
                get("/addCake"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addCake"));
    };

    @Test
    public void testAddCakePageSubmitSuccess() throws Exception {
        this.mockMvc.perform(
                post("/addCake")
                        .param("cake", new Cake("Walnut", "Walnut cake", "imageWalnut.jpg").toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addCake"))
                .andExpect(content().string(containsString(DefaultController.SAVE_SUCCESS_MESSAGE)));
    }

    @Test
    public void testAddCakePageSubmitFailure() throws Exception {
        Cake cake = new Cake("Walnut", "Walnut cake", "imageWalnut.jpg");
        when(_cakeServiceMock.saveCake(any(Cake.class))).thenThrow(new Exception("Cake could not be saved"));

        this.mockMvc.perform(
                post("/addCake")
                        .param("cake", cake.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addCake"))
                .andExpect(content().string(containsString(DefaultController.SAVE_ERROR_MESSAGE)));
    }
}
