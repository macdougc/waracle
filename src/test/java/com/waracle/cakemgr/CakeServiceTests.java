package com.waracle.cakemgr;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import com.waracle.cakemgr.entities.Cake;
import com.waracle.cakemgr.entities.CakeRepository;
import com.waracle.cakemgr.services.WebCakeService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CakeServiceTests {

    @Mock
    private CakeRepository _cakeRepositoryMock;

    private WebCakeService _cakeService;

    @BeforeEach
    public void Initialise() {
        MockitoAnnotations.initMocks(this);
        _cakeService = new WebCakeService(_cakeRepositoryMock);
    }

    @Test
    public void testGetCakes() {
        Cake cake1 = new Cake("Walnut", "Walnut cake", "imageWalnut.jpg");
        Cake cake2 = new Cake("Coffee", "Coffee cake", "imageCoffee.jpg");
        List<Cake> cakeList = new ArrayList<>(List.of(cake1, cake2));

        when(_cakeRepositoryMock.findAll()).thenReturn(cakeList);

        List<Cake> cakeResultList = _cakeService.getCakes();
        Assert.assertTrue(cakeResultList.contains(cake1));
        Assert.assertTrue(cakeResultList.contains(cake2));
    }

    @Test
    public void testSaveCakes() {
        Cake cake1 = new Cake("Walnut", "Walnut cake", "imageWalnut.jpg");

        when(_cakeRepositoryMock.save(cake1)).thenReturn(cake1);

        Cake resultCake = _cakeService.saveCake(cake1);
        Assert.assertEquals(resultCake.getTitle(), cake1.getTitle());
    }
}
