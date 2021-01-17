package com.waracle.cakemgr;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.controllers.CakeController;
import com.waracle.cakemgr.controllers.DefaultController;
import com.waracle.cakemgr.entities.Cake;
import com.waracle.cakemgr.entities.CakeRepository;
import com.waracle.cakemgr.services.CakeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ContextConfiguration(classes=CakeMgrApplication.class)
@AutoConfigureMockMvc
@WebMvcTest(DefaultController.class)
public class CakeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CakeService _cakeServiceMock;

    @MockBean
    private CakeRepository _cakeRepositoryMock;

    @Test
    public void testCakesGet() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cake cake1 = new Cake("Walnut", "Walnut cake", "imageWalnut.jpg");
        Cake cake2 = new Cake("Coffee", "Coffee cake", "imageCoffee.jpg");
        String cakeList = mapper.writeValueAsString(List.of(cake1, cake2));

        when(_cakeServiceMock.getCakes()).thenReturn(new ArrayList<>(List.of(cake1, cake2)));

        this.mockMvc.perform(
                get("/cakes")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(cakeList));
    };

    @Test
    public void testCakesPostSuccess() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cake cake1 = new Cake("Walnut", "Walnut cake", "imageWalnut.jpg");
        String cakeJson = mapper.writeValueAsString(cake1);

        when(_cakeServiceMock.saveCake(any(Cake.class))).thenReturn(cake1);

        this.mockMvc.perform(
                post("/cakes")
                        .content(cakeJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(cakeJson));
    };

    @Test
    public void testCakesPostFail() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cake cake1 = new Cake("Walnut", "Walnut cake", "imageWalnut.jpg");
        String cakeJson = mapper.writeValueAsString(cake1);

        when(_cakeServiceMock.saveCake(any(Cake.class))).thenThrow(new Exception("Cake wasn't saved due to duplicate titles"));

        this.mockMvc.perform(
                post("/cakes")
                        .content(cakeJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(CakeController.SAVE_FAILURE_MESSAGE));
    };
}
